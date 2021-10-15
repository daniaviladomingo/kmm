package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseIn
import kotlinx.coroutines.flow.Flow

class RemoveCharacterFromFavoritesUseCase(
    private val repository: IRepository
): UseCaseIn<Int> {
    override fun execute(param: Int): Flow<Unit> = repository.removeCharacterFromFavorite(param)
}