package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseOutFlow<OUT> {
    abstract operator fun invoke(): Flow<OUT>
}