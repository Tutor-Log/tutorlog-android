package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.local.UIClassInfo
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class RGetEventsUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.getEvents(
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            startDate = request.startDate,
            endDate = request.endDate
        )
            .map { response ->
                if (response.isSuccessful) {
                    val events = response.body()?.map { event ->
                        val (time, meridiem) = parseTime(event.start_time)
                        UIClassInfo(
                            isRepeat = event.repeat_pattern != null && event.repeat_pattern != "none",
                            time = time,
                            meridiem = meridiem,
                            title = event.title.orEmpty(),
                            subtitle = event.event_type.orEmpty(),
                            description = event.description.orEmpty()
                        )
                    } ?: emptyList()
                    Either.Success(UCResponse(eventList = events))
                } else {
                    Either.Error(Exception("Error fetching events: ${response.code()} ${response.message()}"))
                }
            }
    }

    private fun parseTime(startTime: String?): Pair<String, String> {
        if (startTime.isNullOrEmpty()) {
            return Pair("00:00", "AM")
        }
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
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

    data class UCRequest(
        val startDate: String,
        val endDate: String
    )

    data class UCResponse(
        val eventList: List<UIClassInfo>
    )
}
