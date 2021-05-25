package daniel.avila.ricknmortykmm.shared.features.characters

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CharactersPresenter(
    private val navigator: INavigatorCharacters,
) : BasePresenter<ICharactersView>(), ICharactersPresenter<ICharactersView> {

    private val getCharactersUseCase: GetCharactersUseCase by inject()

    override fun loadCharacters() {
        launch {
            getCharactersUseCase
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

    override fun onFavoritesClick() {
        navigator.navigateToFavorites()
    }
}
