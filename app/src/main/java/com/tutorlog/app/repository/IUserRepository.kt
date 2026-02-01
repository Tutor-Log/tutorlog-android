package com.tutorlog.app.repository

import com.tutorlog.app.domain.model.remote.CreateUserPostBody
import com.tutorlog.app.domain.model.remote.AddPupilPostBody
import com.tutorlog.app.domain.model.remote.AddPupilResponse
import com.tutorlog.app.domain.model.remote.AddPupilToGroupPostBody
import com.tutorlog.app.domain.model.remote.AddPupilToGroupResponseItem
import com.tutorlog.app.domain.model.remote.CreateEventPostBody
import com.tutorlog.app.domain.model.remote.CreateEventResponse
import com.tutorlog.app.domain.model.remote.CreateGroupPostBody
import com.tutorlog.app.domain.model.remote.CreateGroupResponse
import com.tutorlog.app.domain.model.remote.EventPupilDetailResponse
import com.tutorlog.app.domain.model.remote.GetAllGroupMemberResponse
import com.tutorlog.app.domain.model.remote.GetEventsResponse
import com.tutorlog.app.domain.model.remote.GetPupilResponse
import com.tutorlog.app.domain.model.remote.HealthResponse
import com.tutorlog.app.domain.model.remote.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IUserRepository {

    suspend fun getHealth(): Flow<Response<HealthResponse>>

    suspend fun getUserById(userId: Int): Flow<Response<UserInfoResponse>>

    suspend fun getAllUsers(): Flow<Response<List<UserInfoResponse>>>

    suspend fun createUser(studentInfo: CreateUserPostBody): Flow<Response<UserInfoResponse>>

    suspend fun addPupil(userId: Int, pupilInfo: AddPupilPostBody): Flow<Response<AddPupilResponse>>

    suspend fun getPupilList(userId: Int): Flow<Response<List<GetPupilResponse>>>

    suspend fun createGroup(userId: Int, groupInfo: CreateGroupPostBody): Flow<Response<CreateGroupResponse>>

    suspend fun getAllGroup(userId: Int): Flow<Response<List<CreateGroupResponse>>>

    suspend fun getAllGroupMember(userId: Int, groupId: Int): Flow<Response<List<GetAllGroupMemberResponse>>>

    suspend fun getPerGroupInfo(userId: Int, groupId: Int): Flow<Response<CreateGroupResponse>>

    suspend fun addPupilToGroup(userId: Int, groupId: Int, pupilList: List<AddPupilToGroupPostBody>): Flow<Response<List<AddPupilToGroupResponseItem>>>

    suspend fun createEvent(userId: Int, createEventPostBody: CreateEventPostBody): Flow<Response<CreateEventResponse>>

    suspend fun getEvents(userId: Int, startDate: String, endDate: String): Flow<Response<List<GetEventsResponse>>>

    suspend fun getEventPupilList(userId: Int, eventId: Int): Flow<Response<List<EventPupilDetailResponse>>>

    suspend fun deleteEvent(eventId: Int, userId: Int): Flow<Response<Unit>>

}