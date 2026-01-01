package com.example.tutorlog.repository

import com.example.tutorlog.domain.remote.CreateUserPostBody
import com.example.tutorlog.domain.remote.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IUserRepository {

    suspend fun getUserById(userId: Int): Flow<Response<UserInfoResponse>>

    suspend fun getAllUsers(): Flow<Response<List<UserInfoResponse>>>

    suspend fun createUser(studentInfo: CreateUserPostBody): Flow<Response<UserInfoResponse>>

}