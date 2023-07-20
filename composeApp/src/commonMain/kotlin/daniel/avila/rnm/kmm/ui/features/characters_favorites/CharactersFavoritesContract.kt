package daniel.avila.rnm.kmm.ui.features.characters_favorites

import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.ui.mvi.BasicUiState
import daniel.avila.rnm.kmm.ui.mvi.UiEffect
import daniel.avila.rnm.kmm.ui.mvi.UiEvent
import daniel.avila.rnm.kmm.ui.mvi.UiState

interface CharactersFavoritesContract {
    sealed interface Event : UiEvent {
        object OnBackPressed : Event
        object OnTryCheckAgainClick : Event
        data class OnCharacterClick(val idCharacter: Int) : Event
    }

    data class State(
        val charactersFavorites: BasicUiState<List<Character>>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idCharacter: Int) : Effect
        object BackNavigation : Effect

    }
}