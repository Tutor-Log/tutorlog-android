package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.model.local.UIGoogleUserInfo
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RGetLoginUseCase @Inject constructor(
    private val userRepository: IUserRepository,
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.getAllUsers()
            .map { response ->
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val uiUserList = users.map { user ->
                        UIGoogleUserInfo(
                            uid = user.id.toString(),
                            googleId = user.google_user_id.orEmpty(),
                            email = user.email.orEmpty(),
                            displayName = user.full_name.orEmpty(),
                            photoUrl = null
                        )
                    }
                    Either.Success(UCResponse(userList = uiUserList))
                } else {
                    Either.Error(Exception("Error fetching users: ${response.code()} ${response.message()}"))
                }
            }
            .catch { e ->
                emit(Either.Error(Exception("Exception occurred: ${e.localizedMessage}")))
            }
    }

    data class UCRequest(
        val nothing: Nothing? = null
    )

    data class UCResponse(
        val userList: List<UIGoogleUserInfo>
    )
}