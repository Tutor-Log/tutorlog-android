package com.example.tutorlog.repository

import com.example.tutorlog.domain.model.remote.AddPupilPostBody
import com.example.tutorlog.domain.model.remote.AddPupilResponse
import com.example.tutorlog.domain.model.remote.AddPupilToGroupPostBody
import com.example.tutorlog.domain.model.remote.AddPupilToGroupResponseItem
import com.example.tutorlog.domain.model.remote.CreateEventPostBody
import com.example.tutorlog.domain.model.remote.CreateEventResponse
import com.example.tutorlog.domain.model.remote.CreateGroupPostBody
import com.example.tutorlog.domain.model.remote.CreateGroupResponse
import com.example.tutorlog.domain.model.remote.CreateUserPostBody
import com.example.tutorlog.domain.model.remote.EventPupilDetailResponse
import com.example.tutorlog.domain.model.remote.GetAllGroupMemberResponse
import com.example.tutorlog.domain.model.remote.GetEventsResponse
import com.example.tutorlog.domain.model.remote.GetPupilResponse
import com.example.tutorlog.domain.model.remote.HealthResponse
import com.example.tutorlog.domain.model.remote.UserInfoResponse
import com.example.tutorlog.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService
) : IUserRepository {

    override suspend fun getHealth(): Flow<Response<HealthResponse>> = flow {
        try {
            val response = userService.getHealth()
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace().toString()
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getAllUsers(): Flow<Response<List<UserInfoResponse>>> = flow {
        try {
            val response = userService.getAllUsers()
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getUserById(userId: Int): Flow<Response<UserInfoResponse>> = flow {
        try {
            val response = userService.getUserById(userId)
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun createUser(studentInfo: CreateUserPostBody): Flow<Response<UserInfoResponse>> =
        flow {
            try {
                val response = userService.createUser(
                    createUserPostBody = studentInfo
                )
                emit(response)
            } catch (e: Exception) {
                emit(
                    Response.error(
                        500,
                        okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                    )
                )
            }
        }

    override suspend fun addPupil(
        userId: Int,
        pupilInfo: AddPupilPostBody
    ): Flow<Response<AddPupilResponse>> = flow {
        try {
            val response = userService.addPupil(
                userId = userId,
                pupilInfo = pupilInfo
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getPupilList(userId: Int): Flow<Response<List<GetPupilResponse>>> = flow {
        try {
            val response = userService.getPupilList(
                userId = userId
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun createGroup(
        userId: Int,
        groupInfo: CreateGroupPostBody
    ): Flow<Response<CreateGroupResponse>> = flow {
        try {
            val response = userService.createGroup(
                userId = userId,
                groupInfo = groupInfo
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getAllGroup(userId: Int): Flow<Response<List<CreateGroupResponse>>> =
        flow {
            try {
                val response = userService.getAllGroup(
                    userId = userId,
                )
                emit(response)
            } catch (e: Exception) {
                emit(
                    Response.error(
                        500,
                        okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                    )
                )
            }
        }

    override suspend fun getAllGroupMember(
        userId: Int,
        groupId: Int
    ): Flow<Response<List<GetAllGroupMemberResponse>>> = flow {
        try {
            val response = userService.getAllGroupMembers(
                userId = userId,
                groupId = groupId
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getPerGroupInfo(
        userId: Int,
        groupId: Int
    ): Flow<Response<CreateGroupResponse>> = flow {
        try {
            val response = userService.getPerGroupInfo(
                userId = userId,
                groupId = groupId
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun addPupilToGroup(
        userId: Int,
        groupId: Int,
        pupilList: List<AddPupilToGroupPostBody>
    ): Flow<Response<List<AddPupilToGroupResponseItem>>> = flow {
        try {
            val response = userService.addPupilToGroup(
                userId = userId,
                groupId = groupId,
                pupilList = pupilList
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun createEvent(
        userId: Int,
        createEventPostBody: CreateEventPostBody
    ): Flow<Response<CreateEventResponse>> = flow {
        try {
            val response = userService.createEvent(
                userId = userId,
                createEventPostBody = createEventPostBody
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getEvents(userId: Int, startDate: String, endDate: String): Flow<Response<List<GetEventsResponse>>> = flow {
        try {
            val response = userService.getEvents(
                userId = userId,
                startDate = startDate,
                endDate = endDate
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun getEventPupilList(
        userId: Int,
        eventId: Int
    ): Flow<Response<List<EventPupilDetailResponse>>> = flow {
        try {
            val response = userService.getEventPupilList(
                userId = userId,
                eventId = eventId,
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }

    override suspend fun deleteEvent(
        eventId: Int,
        userId: Int
    ): Flow<Response<Unit>> = flow {
        try {
            val response = userService.deleteEvent(
                userId = userId,
                eventId = eventId,
            )
            emit(response)
        } catch (e: Exception) {
            emit(
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Exception: ${e.localizedMessage}")
                )
            )
        }
    }
}
