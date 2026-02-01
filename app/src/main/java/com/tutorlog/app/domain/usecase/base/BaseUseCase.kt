package com.tutorlog.app.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class BaseUseCase<I : BaseUseCase.Request, O : BaseUseCase.Response>(private val configuration: Configuration) {

    suspend fun execute(request: I) = process(request).map {
        Either.Success(it) as Either<O>
    }.flowOn(configuration.dispatcher).catch {
        emit(Either.Error(it))
    }

    internal abstract suspend fun process(request: I): Flow<O>

    class Configuration(val dispatcher: CoroutineDispatcher)

    interface Request
    interface Response
}