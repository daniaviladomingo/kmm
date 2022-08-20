package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseIn<IN> {
    operator fun invoke(param: IN): Flow<Resource<Unit>> = flow {
        emit(
            try {
                Resource.Success(block(param))
            } catch (ex: Exception) {
                Resource.Error(exception = ex)
            }
        )
    }
    protected abstract val block: suspend (param: IN) -> Unit
}

abstract class UseCaseInFlow<IN> {
    operator fun invoke(param: IN): Flow<Resource<Unit>> = try {
        build(param).map { Resource.Success(data = it) }
    } catch (ex: Exception) {
        flowOf(Resource.Error(exception = ex))
    }

    protected abstract fun build(param: IN): Flow<Unit>
}