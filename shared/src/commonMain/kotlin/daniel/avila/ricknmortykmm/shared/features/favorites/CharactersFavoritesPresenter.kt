package daniel.avila.ricknmortykmm.shared.features.favorites

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CharactersFavoritesPresenter(
    private val navigator: INavigatorCharactersFavorites
) : BasePresenter<ICharactersFavoritesView>(),
    ICharactersFavoritePresenter<ICharactersFavoritesView> {

    private val getCharactersFavoritesUseCase: GetCharactersFavoritesUseCase by inject()

    override fun loadCharacters() {
        launch {
            getCharactersFavoritesUseCase
                .execute()
                .flowOn(Dispatchers.Unconfined)
                .collect { characters ->
                    view.displayCharacters(characters)
                }
        }
    }

    override fun onCharacterClick(character: Character) {
        navigator.navigateToDetail(character)
    }
}
