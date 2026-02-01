package com.example.tutorlog.feature.event_detail

sealed interface EventDetailSideEffect {

    data class ShowToast(val message: String) : EventDetailSideEffect
}