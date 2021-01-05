package daniel.avila.ricknmortykmm.shared.features.detail

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.Executor
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CharacterDetailPresenter(
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase,
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase,
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase,
    executor: Executor
) : BasePresenter<ICharacterDetailView>(
    executor
), ICharacterDetailPresenter<ICharacterDetailView> {
    override fun isFavorite(idCharacter: Int) {
        launch {
            isCharacterFavoriteUseCase
                .execute(idCharacter)
                .flowOn(Dispatchers.Unconfined)
                .collect { isFavorite ->
                    view.isFavorite(isFavorite)
                }
        }
    }

    override fun addCharacterToFavorite(character: Character) {
        launch {
            addCharacterToFavoritesUseCase
                .execute(character)
                .flowOn(Dispatchers.Unconfined)
                .collect {
                    view.addedFavorite()
                }
        }
    }

    override fun removeCharacterFromFavorite(id: Int) {
        launch {
            removeCharacterFromFavoritesUseCase
                .execute(id)
                .flowOn(Dispatchers.Unconfined)
                .collect {
                    view.removedFavorite()
                }
        }
    }
}
