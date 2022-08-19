package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseOutFlow<OUT> {
    operator fun invoke(): Flow<Resource<OUT>> = try {
        build().map { Resource.Success(data = it) }
    } catch (ex: Exception) {
        flowOf(Resource.Error(exception = ex))
    }

    protected abstract fun build(): Flow<OUT>
}