package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.model.remote.AddPupilPostBody
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class RAddPupilUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    val currentTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.addPupil(
            userId = request.userId,
            pupilInfo = AddPupilPostBody(
                date_of_birth = null,
                email = request.email,
                enrolled_on = currentTime,
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
