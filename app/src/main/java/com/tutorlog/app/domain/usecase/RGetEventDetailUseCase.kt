package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.local_storage.LocalKey
import com.tutorlog.app.domain.local_storage.PreferencesManager
import com.tutorlog.app.domain.model.local.UIPupilInfo
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RGetEventDetailUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {


    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.getEventPupilList(
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            eventId = request.eventId
        ).map {
            if (it.isSuccessful) {
                Either.Success(
                    UCResponse(
                        pupilList = it.body()?.map { item ->
                            UIPupilInfo(
                                createdAt = item.pupil?.created_at.orEmpty(),
                                dateOfBirth = item.pupil?.date_of_birth ?: "",
                                email = item.pupil?.email ?: "",
                                enrolledOn = item.pupil?.enrolled_on ?: "",
                                fatherName = item.pupil?.father_name ?: "",
                                fullName = item.pupil?.full_name ?: "",
                                gender = item.pupil?.gender ?: "",
                                id = item.pupil?.id ?: 0,
                                mobile = item.pupil?.mobile ?: "",
                                motherName = item.pupil?.mother_name ?: "",
                                ownerId = item.pupil?.owner_id ?: 0,
                                updatedAt = item.pupil?.updated_at ?: ""

                            )
                        } ?: emptyList()
                    )
                )
            } else {
                Either.Error(Exception("Error fetching events: ${it.code()} ${it.message()}"))
            }
        }
    }



    data class UCRequest(
        val eventId: Int
    )

    data class UCResponse(
        val pupilList: List<UIPupilInfo>
    )
}