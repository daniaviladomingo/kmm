package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseIn

class RemoveCharacterFromFavoritesUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Int) -> Unit = { repository.removeCharacterFromFavorite(it) },
) : UseCaseIn<Int>()