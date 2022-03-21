package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseInOut
import daniel.avila.ricknmortykmm.shared.domain.model.Character

class GetCharacterUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Int) -> Character = { repository.getCharacter(it) }
) : UseCaseInOut<Int, Character>()