package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RDeleteEventUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager,
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.deleteEvent(
            eventId = request.eventId,
            userId = preferencesManager.getInt(LocalKey.USER_ID)
        ).map {
            if (it.isSuccessful) {
                Either.Success(
                    UCResponse
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }


    data class UCRequest(
        val eventId: Int
    )

    data object UCResponse
}