package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseOut<OUT> {
    fun execute(): Flow<OUT> = flow { emit(block()) }
    protected abstract val block: suspend () -> OUT
}