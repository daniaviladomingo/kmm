package daniel.avila.ricknmortykmm.shared.domain.interactors

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.type.UseCaseOut
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val repository: IRepository
): UseCaseOut<List<Character>> {
    override suspend fun execute(): Flow<List<Character>> = repository.getCharacters()
}