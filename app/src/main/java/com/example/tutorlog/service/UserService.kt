package com.example.tutorlog.service

import com.example.tutorlog.domain.remote.CreateUserPostBody
import com.example.tutorlog.domain.remote.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("user/")
    suspend fun getAllUsers(): Response<List<UserInfoResponse>>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserInfoResponse>

    @POST(value = "user/")
    suspend fun createUser(@Body createUserPostBody: CreateUserPostBody): Response<UserInfoResponse>
}