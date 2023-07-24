package daniel.avila.rnm.kmm.presentation.ui.features.character_detail

import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.presentation.mvi.BasicUiState
import daniel.avila.rnm.kmm.presentation.mvi.UiEffect
import daniel.avila.rnm.kmm.presentation.mvi.UiEvent
import daniel.avila.rnm.kmm.presentation.mvi.UiState

interface CharacterDetailContract {
    sealed interface Event : UiEvent {
        object OnFavoriteClick : Event
        object OnTryCheckAgainClick : Event
        object OnBackPressed : Event
    }

    data class State(
        val character: BasicUiState<Character>,
        val isFavorite: BasicUiState<Boolean>,
    ) : UiState

    sealed interface Effect : UiEffect {
        object CharacterAdded : Effect
        object CharacterRemoved : Effect
        object BackNavigation : Effect
    }
}