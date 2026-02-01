package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.local_storage.LocalKey
import com.tutorlog.app.domain.local_storage.PreferencesManager
import com.tutorlog.app.domain.model.local.UIClassInfo
import com.tutorlog.app.domain.usecase.base.Either
import com.tutorlog.app.repository.IUserRepository
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
                            isRepeat = event.is_repeat_instance == true,
                            time = time,
                            meridiem = meridiem,
                            title = event.title.orEmpty(),
                            subtitle = event.event_type.orEmpty(),
                            description = event.description.orEmpty(),
                            id = event.id ?: 0,
                            endTime = parseTime(event.end_time).first
                        )
                    } ?: emptyList()
                    Either.Success(UCResponse(eventList = events))
                } else {
                    Either.Error(Exception("Error fetching events: ${response.code()} ${response.message()}"))
                }
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
