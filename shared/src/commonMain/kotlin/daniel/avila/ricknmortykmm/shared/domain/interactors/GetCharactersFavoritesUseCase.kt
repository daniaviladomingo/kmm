package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseOut
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseOutFlow
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharactersFavoritesUseCase(
    private val repository: IRepository
) : UseCaseOutFlow<List<Character>>() {
    override fun invoke(): Flow<List<Character>> = repository.getCharactersFavorites()
}