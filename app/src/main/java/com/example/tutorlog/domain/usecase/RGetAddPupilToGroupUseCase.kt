package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.model.remote.GetPupilResponse
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RGetAddPupilToGroupUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        val userId = preferencesManager.getInt(LocalKey.USER_ID)

        val groupMemberResponse = userRepository.getAllGroupMember(
            userId = userId,
            groupId = request.groupId
        )
        val groupInfoResponse = userRepository.getPerGroupInfo(
            userId = userId,
            groupId = request.groupId
        )
        val pupilResponse = userRepository.getPupilList(
            userId = userId
        )

        return combine(groupMemberResponse, groupInfoResponse, pupilResponse) { groupMember, groupInfo, pupil ->
            if (groupMember.isSuccessful && groupInfo.isSuccessful && pupil.isSuccessful) {
                // Get IDs of existing group members
                val groupMemberIds = groupMember.body()?.mapNotNull { item ->
                    item.pupil_details?.toUIPupilInfo()?.id
                }?.toSet() ?: emptySet()

                // Filter all pupils to exclude those already in the group and map to UIAdditionPupil
                val pupilList = pupil.body()?.map { it.toUIPupilInfo() }
                    ?.filter { it.id !in groupMemberIds }
                    ?.map {
                        UIAdditionPupil(
                            id = it.id,
                            name = it.fullName,
                            isSelected = false,
                            details = it.email
                        )
                    }
                    ?: emptyList()

                Either.Success(
                    data = UCResponse(
                        groupName = groupInfo.body()?.name.orEmpty(),
                        groupDescription = groupInfo.body()?.description.orEmpty(),
                        pupilList = pupilList
                    )
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }

    private fun GetPupilResponse.toUIPupilInfo(): UIPupilInfo {
        return UIPupilInfo(
            createdAt = created_at ?: "",
            dateOfBirth = date_of_birth ?: "",
            email = email ?: "",
            enrolledOn = enrolled_on ?: "",
            fatherName = father_name ?: "",
            fullName = full_name ?: "",
            gender = gender ?: "",
            id = id ?: 0,
            mobile = mobile ?: "",
            motherName = mother_name ?: "",
            ownerId = owner_id ?: 0,
            updatedAt = updated_at ?: ""
        )
    }

    data class UCRequest(
        val groupId: Int
    )

    data class UCResponse(
        val groupName: String,
        val groupDescription: String,
        val pupilList: List<UIAdditionPupil>
    )
}