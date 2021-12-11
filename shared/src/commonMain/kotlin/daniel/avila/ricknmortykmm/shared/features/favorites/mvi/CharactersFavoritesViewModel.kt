package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.StateRequest
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
            charactersFavorites = emptyList(),
            stateRequest = StateRequest.Idle
        )

    override fun handleEvent(event: CharactersFavoritesContract.Event) {
        when (event) {
            CharactersFavoritesContract.Event.OnGetCharactersFavorites -> getCharactersFavorites()
        }
    }

    private fun getCharactersFavorites() {
        setState { copy(stateRequest = StateRequest.Loading) }
        launch(getCharactersFavoritesUseCase.execute(), { favorites ->
            setState {
                copy(
                    charactersFavorites = favorites,
                    stateRequest =
                    if (favorites.isEmpty())
                        StateRequest.Empty()
                    else
                        StateRequest.Success
                )
            }
        }, {
            setState { copy(stateRequest = StateRequest.Error()) }
        })
    }
}