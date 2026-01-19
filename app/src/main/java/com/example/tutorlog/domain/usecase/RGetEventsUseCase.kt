package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.model.local.UIPupilClassInfo
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
) {
    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.getEvents(
            ownerId = request.ownerId
        )
            .map { response ->
                if (response.isSuccessful) {
                    val events = response.body()?.map { event ->
                        val (time, meridiem) = parseTime(event.start_time)
                        UIPupilClassInfo(
                            isRepeat = event.repeat_pattern != null && event.repeat_pattern != "none",
                            time = time,
                            meridiem = meridiem,
                            title = event.title.orEmpty(),
                            subtitle = event.event_type.orEmpty(),
                            description = event.description.orEmpty()
                        )
                    } ?: emptyList()
                    Either.Success(UCResponse(events = events))
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
            val date = inputFormat.parse(startTime)
            
            val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
            val meridiemFormat = SimpleDateFormat("a", Locale.getDefault())
            
            val time = timeFormat.format(date!!)
            val meridiem = meridiemFormat.format(date).uppercase()
            
            Pair(time, meridiem)
        } catch (e: Exception) {
            Pair("00:00", "AM")
        }
    }

    data class UCRequest(
        val ownerId: Int
    )

    data class UCResponse(
        val events: List<UIPupilClassInfo>
    )
}
