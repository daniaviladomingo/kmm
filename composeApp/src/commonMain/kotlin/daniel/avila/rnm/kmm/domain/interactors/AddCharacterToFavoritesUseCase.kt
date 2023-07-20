package daniel.avila.rnm.kmm.domain.interactors

import daniel.avila.rnm.kmm.domain.IRepository
import daniel.avila.rnm.kmm.domain.interactors.type.BaseUseCase
import daniel.avila.rnm.kmm.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher

class AddCharacterToFavoritesUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Character, Unit>(dispatcher) {
    override suspend fun block(param: Character) = repository.addCharacterToFavorites(param)
}