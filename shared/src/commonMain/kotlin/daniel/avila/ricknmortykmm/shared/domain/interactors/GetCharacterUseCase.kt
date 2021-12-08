package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseInOut
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: IRepository
) : UseCaseInOut<Int, Character> {
    override fun execute(param: Int): Flow<Character> = repository.getCharacter(param)
}