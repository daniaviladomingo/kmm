package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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