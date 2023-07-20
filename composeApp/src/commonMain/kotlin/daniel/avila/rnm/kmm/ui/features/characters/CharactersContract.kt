package daniel.avila.rnm.kmm.ui.features.characters

import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.ui.mvi.BasicUiState
import daniel.avila.rnm.kmm.ui.mvi.UiEffect
import daniel.avila.rnm.kmm.ui.mvi.UiEvent
import daniel.avila.rnm.kmm.ui.mvi.UiState

interface CharactersContract {
    sealed interface Event : UiEvent {
        object OnTryCheckAgainClick : Event
        object OnFavoritesClick : Event
        data class OnCharacterClick(val idCharacter: Int) : Event
    }

    data class State(
        val characters: BasicUiState<List<Character>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idCharacter: Int) : Effect
        object NavigateToFavorites : Effect
    }
}


