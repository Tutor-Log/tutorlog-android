package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.remote.CreateEventPostBody
import com.example.tutorlog.domain.model.remote.EventData
import com.example.tutorlog.domain.types.EventFrequencyType
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RCreateEventUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager,
) {

    suspend fun process(request: UCRequest): Flow<Either<UCResponse>> {
        return userRepository.createEvent(
            userId = preferencesManager.getInt(LocalKey.USER_ID),
            createEventPostBody = CreateEventPostBody(
                event_data = EventData(
                    title = request.title,
                    description = request.description,
                    start_time = request.startTime,
                    end_time = request.endTime,
                    owner_id = preferencesManager.getInt(LocalKey.USER_ID),
                    repeat_until = request.repeatUntil,
                    repeat_pattern = "custom_days",
                    event_type = request.eventType,
                ),
                repeat_days = request.repeatDays,
                pupil_ids = request.selectedPupils
            )
        ).map {
            if (it.isSuccessful) {
                Either.Success(
                    UCResponse
                )
            } else {
                Either.Error(throwable = Exception())
            }
        }
    }




    data class UCRequest(
        val title: String,
        val description: String,
        val eventType: String,
        val startTime: String,
        val endTime: String,
        val repeatUntil: String?,
        val repeatDays: List<Int>,
        val selectedPupils: List<Int>,
    )

    data object UCResponse
}