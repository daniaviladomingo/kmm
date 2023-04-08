package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEvent
import daniel.avila.ricknmortykmm.shared.base.mvi.UiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

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