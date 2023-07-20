package daniel.avila.rnm.kmm.domain.interactors.type

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class BaseUseCaseFlow<IN, OUT>(
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun  invoke(param: IN): Flow<Result<OUT>> = build(param)
        .flowOn(dispatcher)
        .map {
            Result.success(it)
        }.catch { emit(Result.failure(it)) }

    protected abstract suspend fun build(param: IN): Flow<OUT>
}