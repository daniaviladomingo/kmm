package daniel.avila.rnm.kmm.domain.interactors

import daniel.avila.rnm.kmm.domain.IRepository
import daniel.avila.rnm.kmm.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class RemoveCharacterFromFavoritesUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Int,Unit>(dispatcher){
    override suspend fun block(param: Int) = repository.removeCharacterFromFavorite(param)
}