package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseIn<in IN> {
    fun execute(param: IN): Flow<Unit>
}