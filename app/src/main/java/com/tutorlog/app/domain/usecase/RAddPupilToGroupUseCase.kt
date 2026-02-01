package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.local_storage.LocalKey
import com.tutorlog.app.domain.local_storage.PreferencesManager
import com.tutorlog.app.domain.model.remote.AddPupilToGroupPostBody
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RAddPupilToGroupUseCase @Inject constructor(
    private val userRespository: IUserRepository,
    private val preferencesManager: PreferencesManager
){

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        val list = request.pupilIdList.map {
            AddPupilToGroupPostBody(
                group_id = request.groupId,
                pupil_id = it
            )
        }
        return userRespository.addPupilToGroup(
            groupId = request.groupId,
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            pupilList = list
        ).map {
            if (it.isSuccessful) {
                Either.Success(
                    data = UCResponse
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }


    data class UCRequest(
        val pupilIdList: List<Int>,
        val groupId: Int
    )

    data object UCResponse
}