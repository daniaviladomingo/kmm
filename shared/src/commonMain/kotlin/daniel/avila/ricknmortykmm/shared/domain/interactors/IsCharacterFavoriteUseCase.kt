package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseInOut

class IsCharacterFavoriteUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Int) -> Boolean = { repository.isCharacterFavorite(it) }
) : UseCaseInOut<Int, Boolean>()