package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseOut
import daniel.avila.ricknmortykmm.shared.domain.model.Character

class GetCharactersUseCase(
    private val repository: IRepository,
    override val block: suspend () -> List<Character> = { repository.getCharacters() },
) : UseCaseOut<List<Character>>()