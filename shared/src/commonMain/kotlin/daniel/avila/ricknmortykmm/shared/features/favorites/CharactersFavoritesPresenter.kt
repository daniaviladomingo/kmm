package daniel.avila.ricknmortykmm.shared.features.favorites

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.Executor
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CharactersFavoritesPresenter(
    private val getCharactersUseCase: GetCharactersFavoritesUseCase,
    private val navigator: INavigatorCharactersFavorites,
    executor: Executor
) : BasePresenter<ICharactersFavoritesView>(
    executor
), ICharactersFavoritePresenter<ICharactersFavoritesView> {
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
}
