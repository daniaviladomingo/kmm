package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCase {
    operator fun invoke(): Flow<Resource<Unit>> = flow {
        emit(
            try {
                Resource.Success(block())
            } catch (ex: Exception) {
                Resource.Error(exception = ex)
            }
        )
    }

    protected abstract val block: suspend () -> Unit
}