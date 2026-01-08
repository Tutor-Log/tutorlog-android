package com.example.tutorlog.domain.usecase.base

import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RGetBEHealth @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {

        return userRepository.getHealth().map {
            if (it.isSuccessful) {
                Either.Success(
                    UCResponse
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }


    data object UCRequest
    data object UCResponse
}