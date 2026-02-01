package com.example.tutorlog.domain.usecase.base

sealed class Either<out T : Any> {

    data class Success<out T : Any>(val data: T) : Either<T>()
    class Error(val throwable: Throwable,val errorCode: Int? = null, val errorMessage: String? = null) :
        Either<Nothing>()
}