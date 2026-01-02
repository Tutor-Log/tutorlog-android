package com.example.tutorlog.service

import com.example.tutorlog.domain.model.remote.AddPupilPostBody
import com.example.tutorlog.domain.model.remote.AddPupilResponse
import com.example.tutorlog.domain.model.remote.CreateUserPostBody
import com.example.tutorlog.domain.model.remote.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("user/")
    suspend fun getAllUsers(): Response<List<UserInfoResponse>>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserInfoResponse>

    @POST(value = "user/")
    suspend fun createUser(@Body createUserPostBody: CreateUserPostBody): Response<UserInfoResponse>

    @POST("pupil/")
    suspend fun addPupil(
        @Query("current_user_id") userId: Int,
        @Body pupilInfo: AddPupilPostBody
    ): Response<AddPupilResponse>
}