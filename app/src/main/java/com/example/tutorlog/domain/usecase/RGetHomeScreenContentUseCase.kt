package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local.UIUserInfo
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RGetHomeScreenContentUseCase @Inject constructor(
    private val userRepository: IUserRepository,
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.getUserById(
            userId = request.userId
        )
            .map { response ->
                if (response.isSuccessful) {
                    val user = UIUserInfo(
                        id = response.body()?.id ?: 0,
                        googleId = response.body()?.google_user_id.orEmpty(),
                        name = response.body()?.full_name.orEmpty().toTitleCase(),
                        email = response.body()?.email.orEmpty(),
                        iamge = response.body()?.profile_pic_url.orEmpty()
                    )
                    Either.Success(UCResponse(userInfo = user))
                } else {
                    Either.Error(Exception("Error fetching users: ${response.code()} ${response.message()}"))
                }
            }
    }

    data class UCRequest(
        val userId: Int
    )

    data class UCResponse(
        val userInfo: UIUserInfo
    )
}

fun String.toTitleCase(): String {
    return this.lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { it.uppercase() }
        }
}