package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseInOut<IN, OUT> {
    operator fun invoke(param: IN): Flow<Resource<OUT>> = flow {
        emit(
            try {
                Resource.Success(block(param))
            } catch (ex: Exception) {
                Resource.Error(exception = ex)
            }
        )
    }

    protected abstract val block: suspend (param: IN) -> OUT
}

abstract class UseCaseInOutFlow<IN, OUT> {
    operator fun invoke(param: IN): Flow<Resource<OUT>> = try {
        build(param).map { Resource.Success(data = it) }
    } catch (ex: Exception) {
        flowOf(Resource.Error(exception = ex))
    }

    protected abstract fun build(param: IN): Flow<OUT>
}