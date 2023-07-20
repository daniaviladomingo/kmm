package daniel.avila.rnm.kmm.domain.interactors

import daniel.avila.rnm.kmm.domain.IRepository
import daniel.avila.rnm.kmm.domain.interactors.type.BaseUseCaseFlow
import daniel.avila.rnm.kmm.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetCharactersFavoritesUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit,List<Character>>(dispatcher) {
    override suspend fun build(param: Unit): Flow<List<Character>> = repository.getCharactersFavorites()
}