package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.model.local.UIUserInfo
import com.tutorlog.app.domain.model.remote.CreateUserPostBody
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RCreateUserUseCase @Inject constructor(
    private val userRepository: IUserRepository,
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.createUser(
            studentInfo = CreateUserPostBody(
                google_user_id = request.googleId,
                email = request.email,
                full_name = request.name,
                profile_pic_url = request.image
            )
        ).map { response ->
            if (response.isSuccessful) {
                val user = UIUserInfo(
                    googleId = response.body()?.google_user_id.orEmpty(),
                    name = response.body()?.full_name.orEmpty(),
                    email = response.body()?.email.orEmpty(),
                    iamge = response.body()?.profile_pic_url.orEmpty(),
                    id = response.body()?.id ?: 0
                )
                Either.Success(UCResponse(userInfo = user))
            } else {
                Either.Error(throwable = Exception(response.message()), errorCode = response.code())
            }
        }
    }


    data class UCRequest(
        val email: String, val name: String, val googleId: String, val image: String?
    )

    data class UCResponse(
        val userInfo: UIUserInfo
    )
}