package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.local_storage.LocalKey
import com.tutorlog.app.domain.local_storage.PreferencesManager
import com.tutorlog.app.domain.model.local.UIPupilInfo
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RGetGroupDetailUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>>{
        val groupMemberResponse = userRepository.getAllGroupMember(
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            groupId = request.groupId
        )
        val groupInfoResponse = userRepository.getPerGroupInfo(
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            groupId = request.groupId
        )
        return combine(groupMemberResponse, groupInfoResponse) { groupMember, groupInfo ->
            if (groupMember.isSuccessful && groupInfo.isSuccessful) {
                Either.Success(
                    data = UCResponse(
                        groupName = groupInfo.body()?.name.orEmpty(),
                        groupDescription = groupInfo.body()?.description.orEmpty(),
                        pupilList = groupMember.body()?.map { item ->
                            UIPupilInfo(
                                createdAt = item.pupil_details?.created_at ?: "",
                                dateOfBirth = item.pupil_details?.date_of_birth ?: "",
                                email = item.pupil_details?.email ?: "",
                                enrolledOn = item.pupil_details?.enrolled_on ?: "",
                                fatherName = item.pupil_details?.father_name ?: "",
                                fullName = item.pupil_details?.full_name ?: "",
                                gender = item.pupil_details?.gender ?: "",
                                id = item.pupil_details?.id ?: 0,
                                mobile = item.pupil_details?.mobile ?: "",
                                motherName = item.pupil_details?.mother_name ?: "",
                                ownerId = item.pupil_details?.owner_id ?: 0,
                                updatedAt = item.pupil_details?.updated_at ?: ""

                            )
                        } ?: emptyList()
                    )
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }

    data class UCRequest(
        val groupId: Int
    )

    data class UCResponse(
        val groupName: String,
        val groupDescription: String,
        val pupilList: List<UIPupilInfo>,
    )
}