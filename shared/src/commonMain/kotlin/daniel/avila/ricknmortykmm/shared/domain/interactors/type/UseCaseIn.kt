package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseIn<IN> {
    operator fun invoke(param: IN): Flow<Unit> = flow { emit(block(param)) }
    protected abstract val block: suspend (param: IN) -> Unit
}