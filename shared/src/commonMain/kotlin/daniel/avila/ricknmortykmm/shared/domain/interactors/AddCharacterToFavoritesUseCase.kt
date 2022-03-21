package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseIn
import daniel.avila.ricknmortykmm.shared.domain.model.Character

class AddCharacterToFavoritesUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Character) -> Unit = { repository.addCharacterToFavorites(it) }
) : UseCaseIn<Character>()