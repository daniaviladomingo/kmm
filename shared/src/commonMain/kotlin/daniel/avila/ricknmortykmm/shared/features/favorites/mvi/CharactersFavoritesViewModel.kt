package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersFavoritesUseCase
import org.koin.core.component.inject

open class CharactersFavoritesViewModel :
    BaseViewModel<CharactersFavoritesContract.Event, CharactersFavoritesContract.State, UiEffect>() {
    private val getCharactersFavoritesUseCase: GetCharactersFavoritesUseCase by inject()

    init {
        getCharactersFavorites()
    }

    override fun createInitialState(): CharactersFavoritesContract.State =
        CharactersFavoritesContract.State(
            charactersFavorites = BasicUiState.Idle
        )

    override fun handleEvent(event: CharactersFavoritesContract.Event) {
        when (event) {
            // Hola
            CharactersFavoritesContract.Event.OnGetCharactersFavorites -> getCharactersFavorites()
        }
    }

    private fun getCharactersFavorites() {
        setState { copy(charactersFavorites = BasicUiState.Loading) }
        launch(getCharactersFavoritesUseCase.execute(), { favorites ->
            setState {
                copy(
                    charactersFavorites =
                    if (favorites.isEmpty())
                        BasicUiState.Empty
                    else
                        BasicUiState.Success(favorites)
                )
            }
        }, {
            setState { copy(charactersFavorites = BasicUiState.Error()) }
        })
    }
}