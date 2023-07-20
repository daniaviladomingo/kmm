package daniel.avila.rnm.kmm.domain.interactors

import daniel.avila.rnm.kmm.domain.IRepository
import daniel.avila.rnm.kmm.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class IsCharacterFavoriteUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Int, Boolean>(dispatcher) {
    override suspend fun block(param: Int): Boolean = repository.isCharacterFavorite(param)
}