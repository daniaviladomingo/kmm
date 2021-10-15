package daniel.avila.ricknmortykmm.shared.domain.interactors.type

import kotlinx.coroutines.flow.Flow

interface UseCaseInOut<in IN, out OUT> {
    fun execute(param: IN): Flow<OUT>
}