package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseInOut<IN, OUT> {
    operator fun invoke(param: IN): Flow<Result<OUT>> = flow {
        emit(
            try {
                Result.success(block(param))
            } catch (ex: Exception) {
                Result.failure(exception = ex)
            }
        )
    }

    protected abstract val block: suspend (param: IN) -> OUT
}

abstract class UseCaseInOutFlow<IN, OUT> {
    operator fun invoke(param: IN): Flow<Result<OUT>> = try {
        build(param).map { Result.success(it) }
    } catch (ex: Exception) {
        flowOf(Result.failure(exception = ex))
    }

    protected abstract fun build(param: IN): Flow<OUT>
}