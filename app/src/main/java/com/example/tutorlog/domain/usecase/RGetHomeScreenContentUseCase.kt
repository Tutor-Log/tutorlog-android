package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.local.UIClassInfo
import com.example.tutorlog.domain.model.local.UIUserInfo
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class RGetHomeScreenContentUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        val userId = preferencesManager.getInt(LocalKey.USER_ID)
        
        val userFlow = userRepository.getUserById(userId = userId)
        val eventsFlow = userRepository.getEvents(
            userId = userId,
            startDate = request.startDate,
            endDate = request.endDate
        )
        
        return userFlow.combine(eventsFlow) { userResponse, eventsResponse ->
            if (userResponse.isSuccessful) {
                val user = UIUserInfo(
                    id = userResponse.body()?.id ?: 0,
                    googleId = userResponse.body()?.google_user_id.orEmpty(),
                    name = userResponse.body()?.full_name.orEmpty().toTitleCase(),
                    email = userResponse.body()?.email.orEmpty(),
                    iamge = userResponse.body()?.profile_pic_url.orEmpty()
                )
                
                val events = if (eventsResponse.isSuccessful) {
                    eventsResponse.body()?.map { event ->
                        val (time, meridiem) = parseTime(event.start_time)
                        UIClassInfo(
                            isRepeat = event.is_repeat_instance == true,
                            time = time,
                            meridiem = meridiem,
                            title = event.title.orEmpty(),
                            subtitle = event.event_type.orEmpty(),
                            description = event.description.orEmpty(),
                            id = event.id ?: 0,
                            endTime = parseTime(event.end_time.orEmpty()).first
                        )
                    } ?: emptyList()
                } else {
                    emptyList()
                }
                
                Either.Success(UCResponse(userInfo = user, eventList = events))
            } else {
                Either.Error(Exception("Error fetching users: ${userResponse.code()} ${userResponse.message()}"))
            }
        }
    }

    data class UCRequest(
        val startDate: String,
        val endDate: String
    )

    data class UCResponse(
        val userInfo: UIUserInfo,
        val eventList: List<UIClassInfo>
    )
}

fun String.toTitleCase(): String {
    return this.lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { it.uppercase() }
        }
}

fun parseTime(startTime: String?): Pair<String, String> {
    if (startTime.isNullOrEmpty()) {
        return Pair("00:00", "AM")
    }
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(startTime) ?: return Pair("00:00", "AM")

        val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
        val meridiemFormat = SimpleDateFormat("a", Locale.getDefault())

        val time = timeFormat.format(date)
        val meridiem = meridiemFormat.format(date).uppercase()

        Pair(time, meridiem)
    } catch (e: Exception) {
        Pair("00:00", "AM")
    }
}