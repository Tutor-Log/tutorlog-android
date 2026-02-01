package com.tutorlog.app.service

import com.tutorlog.app.domain.model.remote.AddPupilPostBody
import com.tutorlog.app.domain.model.remote.AddPupilResponse
import com.tutorlog.app.domain.model.remote.AddPupilToGroupPostBody
import com.tutorlog.app.domain.model.remote.AddPupilToGroupResponseItem
import com.tutorlog.app.domain.model.remote.CreateEventPostBody
import com.tutorlog.app.domain.model.remote.CreateEventResponse
import com.tutorlog.app.domain.model.remote.CreateGroupPostBody
import com.tutorlog.app.domain.model.remote.GetEventsResponse
import com.tutorlog.app.domain.model.remote.CreateGroupResponse
import com.tutorlog.app.domain.model.remote.CreateUserPostBody
import com.tutorlog.app.domain.model.remote.EventPupilDetailResponse
import com.tutorlog.app.domain.model.remote.GetAllGroupMemberResponse
import com.tutorlog.app.domain.model.remote.GetPupilResponse
import com.tutorlog.app.domain.model.remote.HealthResponse
import com.tutorlog.app.domain.model.remote.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("health")
    suspend fun getHealth(): Response<HealthResponse>

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

    @GET("pupil/")
    suspend fun getPupilList(
        @Query("current_user_id") userId: Int,
    ): Response<List<GetPupilResponse>>

    @POST("group/")
    suspend fun createGroup(
        @Query("current_user_id") userId: Int,
        @Body groupInfo: CreateGroupPostBody
    ): Response<CreateGroupResponse>

    @GET(value = "group/")
    suspend fun getAllGroup(
        @Query("current_user_id") userId: Int
    ): Response<List<CreateGroupResponse>>

    @GET("group/{groupId}/members")
    suspend fun getAllGroupMembers(
        @Path("groupId") groupId: Int,
        @Query("current_user_id") userId: Int
    ): Response<List<GetAllGroupMemberResponse>>

    @GET("group/{groupId}")
    suspend fun getPerGroupInfo(
        @Path("groupId") groupId: Int,
        @Query("current_user_id") userId: Int
    ): Response<CreateGroupResponse>

    @POST("group/{groupId}/members")
    suspend fun addPupilToGroup(
        @Path("groupId") groupId: Int,
        @Query("current_user_id") userId: Int,
        @Body pupilList: List<AddPupilToGroupPostBody>
    ): Response<List<AddPupilToGroupResponseItem>>


    @POST("events/")
    suspend fun createEvent(
        @Query("current_user_id") userId: Int,
        @Body createEventPostBody: CreateEventPostBody
    ): Response<CreateEventResponse>

    @GET("events/")
    suspend fun getEvents(
        @Query("owner_id") userId: Int,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Response<List<GetEventsResponse>>

    @GET("events/{eventId}/pupils")
    suspend fun getEventPupilList(
        @Path("eventId") eventId: Int,
        @Query("current_user_id") userId: Int,
    ): Response<List<EventPupilDetailResponse>>

    @DELETE("events/{eventId}")
    suspend fun deleteEvent(
        @Path("eventId") eventId: Int,
        @Query("current_user_id") userId: Int,
    ): Response<Unit>
}