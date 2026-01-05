package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.local.UIGroupInfo
import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RGetStudentGroupUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        val pupilResponse = userRepository.getPupilList(
            userId = preferencesManager.getInt(LocalKey.USER_ID)
        )
        val groupResponse = userRepository.getAllGroup(
            userId = preferencesManager.getInt(LocalKey.USER_ID)
        )
        return combine(pupilResponse, groupResponse) { pupil, group ->
            if (pupil.isSuccessful && group.isSuccessful) {
                val pupilList = pupil.body()?.map {
                    UIPupilInfo(
                        createdAt = it.created_at ?: "",
                        dateOfBirth = it.date_of_birth ?: "",
                        email = it.email ?: "",
                        enrolledOn = it.enrolled_on ?: "",
                        fatherName = it.father_name ?: "",
                        fullName = it.full_name ?: "",
                        gender = it.gender ?: "",
                        id = it.id ?: 0,
                        mobile = it.mobile ?: "",
                        motherName = it.mother_name ?: "",
                        ownerId = it.owner_id ?: 0,
                        updatedAt = it.updated_at ?: ""


                    )
                } ?: emptyList()
                val groupList = group.body()?.map {
                    UIGroupInfo(
                        name = it.name.orEmpty(),
                        description = it.name.orEmpty(),
                        groupId = it.id ?: 0
                    )
                } ?: emptyList()
                Either.Success(
                    data = UCResponse(
                        pupilList = pupilList,
                        groupList = groupList
                    )
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }


    data object UCRequest

    data class UCResponse(
        val pupilList: List<UIPupilInfo>,
        val groupList: List<UIGroupInfo>
    )

}