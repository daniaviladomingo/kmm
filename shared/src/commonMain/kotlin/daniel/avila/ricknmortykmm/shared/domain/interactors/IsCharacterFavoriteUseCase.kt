package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseInOut
import kotlinx.coroutines.flow.Flow

class IsCharacterFavoriteUseCase(
    private val repository: IRepository
): UseCaseInOut<Int, Boolean> {
    override suspend fun execute(param: Int): Flow<Boolean> = repository.isCharacterFavorite(param)
}