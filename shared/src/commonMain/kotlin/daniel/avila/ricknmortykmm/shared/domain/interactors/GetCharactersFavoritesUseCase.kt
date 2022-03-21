package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseOut
import daniel.avila.ricknmortykmm.shared.domain.model.Character

class GetCharactersFavoritesUseCase(
    private val repository: IRepository,
    override val block: suspend () -> List<Character> = { repository.getCharactersFavorites() }
) : UseCaseOut<List<Character>>()