package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.local_storage.LocalKey
import com.tutorlog.app.domain.local_storage.PreferencesManager
import com.tutorlog.app.domain.model.local.UIGroupInfo
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RGetAllGroupListUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.getAllGroup(
            userId = preferencesManager.getInt(LocalKey.USER_ID)
        ).map { response ->
            if (response.isSuccessful) {
                Either.Success(
                    data = UCResponse(
                        groupList = response.body()?.map {
                            UIGroupInfo(
                                name = it.name.orEmpty(),
                                description = it.name.orEmpty(),
                                groupId = it.id ?: 0
                            )
                        } ?: emptyList()
                    )
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }



    data object UCRequest

    data class UCResponse(
        val groupList: List<UIGroupInfo>
    )
}