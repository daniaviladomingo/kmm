package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCase {
    fun execute(): Flow<Unit> = flow { emit(block()) }
    protected abstract val block: suspend () -> Unit
}