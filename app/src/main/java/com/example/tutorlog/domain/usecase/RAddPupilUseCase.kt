package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.model.remote.AddPupilPostBody
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RAddPupilUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.addPupil(
            userId = request.userId,
            pupilInfo = AddPupilPostBody(
                date_of_birth = null,
                email = request.email,
                enrolled_on = null,
                father_name = null,
                full_name = request.name,
                gender = null,
                mobile = request.phone,
                mother_name = null,
                owner_id = request.userId
            )
        ).map {
            if (it.isSuccessful) {
                Either.Success(UCResponse(message = it.message()))
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }

    data class UCRequest(
        val name: String,
        val email: String,
        val phone: String,
        val group: String?,
        val userId: Int
    )
    data class UCResponse(
        val message: String
    )
}
