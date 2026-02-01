package com.tutorlog.app.feature.event_detail

sealed interface EventDetailSideEffect {

    data class ShowToast(val message: String) : EventDetailSideEffect
}