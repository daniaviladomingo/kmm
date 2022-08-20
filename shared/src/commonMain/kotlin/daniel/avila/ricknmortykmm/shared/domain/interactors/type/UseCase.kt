package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

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

abstract class UseCaseFlow {
    operator fun invoke(): Flow<Resource<Unit>> = try {
        build().map { Resource.Success(data = it) }
    } catch (ex: Exception) {
        flowOf(Resource.Error(exception = ex))
    }

    protected abstract fun build(): Flow<Unit>
}