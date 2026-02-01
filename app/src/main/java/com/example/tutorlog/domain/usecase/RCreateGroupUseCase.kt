package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.remote.CreateGroupPostBody
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RCreateGroupUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager,
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.createGroup(
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            groupInfo = CreateGroupPostBody(
                name = request.name,
                description = request.description,
                owner_id = preferencesManager.getInt(LocalKey.USER_ID),

            )
        ).map { response ->
            if (response.isSuccessful) {
                Either.Success(
                    data = UCResponse
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }


    data class UCRequest(
        val name: String,
        val description: String,
    )

    data object UCResponse
}