package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseIn
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseOut
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

class AddCharacterToFavoritesUseCase(
    private val repository: IRepository
): UseCaseIn<Character> {
    override suspend fun execute(param: Character): Flow<Unit> = repository.addCharacterToFavorites(param)

}