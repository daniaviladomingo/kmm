package daniel.avila.ricknmortykmm.shared.features.characters

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.Executor
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CharactersPresenter(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val navigator: INavigatorCharacters,
    executor: Executor
) : BasePresenter<ICharactersView>(
    executor
), ICharactersPresenter<ICharactersView> {
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
