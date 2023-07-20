package daniel.avila.rnm.kmm.ui.features.character_detail

import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.ui.mvi.BasicUiState
import daniel.avila.rnm.kmm.ui.mvi.UiEffect
import daniel.avila.rnm.kmm.ui.mvi.UiEvent
import daniel.avila.rnm.kmm.ui.mvi.UiState

interface CharacterDetailContract {
    sealed interface Event : UiEvent {
        data class GetCharacter(val idCharacter: Int) : Event
        object OnAddCharacterToFavoriteClick : Event
        object OnRemoveCharacterFromFavoriteClick : Event
        object OnTryCheckAgainClick : Event
        object OnBackPressed : Event
    }

    data class State(
        val character: BasicUiState<Character>,
        val isFavorite: Boolean,
    ) : UiState

    sealed interface Effect : UiEffect {
        object CharacterAdded : Effect
        object CharacterRemoved : Effect
        object BackNavigation : Effect
    }
}