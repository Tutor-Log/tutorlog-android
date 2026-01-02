package com.example.tutorlog.repository

import com.example.tutorlog.domain.model.remote.CreateUserPostBody
import com.example.tutorlog.domain.model.remote.AddPupilPostBody
import com.example.tutorlog.domain.model.remote.AddPupilResponse
import com.example.tutorlog.domain.model.remote.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IUserRepository {

    suspend fun getUserById(userId: Int): Flow<Response<UserInfoResponse>>

    suspend fun getAllUsers(): Flow<Response<List<UserInfoResponse>>>

    suspend fun createUser(studentInfo: CreateUserPostBody): Flow<Response<UserInfoResponse>>

    suspend fun addPupil(userId: Int, pupilInfo: AddPupilPostBody): Flow<Response<AddPupilResponse>>

}