package daniel.avila.ricknmortykmm.shared.features.detail

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CharacterDetailPresenter : BasePresenter<ICharacterDetailView>(), ICharacterDetailPresenter<ICharacterDetailView> {

    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase by inject()
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase by inject()
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase by inject()

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
