package com.example.tutorlog.repository

import com.example.tutorlog.domain.remote.CreateUserPostBody
import com.example.tutorlog.domain.remote.UserInfoResponse
import com.example.tutorlog.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService
): IUserRepository {
    override suspend fun getAllUsers(): Flow<Response<List<UserInfoResponse>>> = flow {
        try {
            val response = userService.getAllUsers()
            emit(response)
        } catch (e: Exception) {
            emit(Response.error(500, okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")))
        }
    }

    override suspend fun getUserById(userId: Int): Flow<Response<UserInfoResponse>> = flow {
        try {
            val response = userService.getUserById(userId)
            emit(response)
        } catch (e: Exception) {
            emit(Response.error(500, okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")))
        }
    }

    override suspend fun createUser(studentInfo: CreateUserPostBody): Flow<Response<UserInfoResponse>> = flow {
        try {
            val response = userService.createUser(
                createUserPostBody = studentInfo
            )
            emit(response)
        } catch (e: Exception) {
            emit(Response.error(500, okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")))
        }
    }
}
