package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.*
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharactersFavoritesContract {
    sealed class Event : UiEvent {
        object OnGetCharactersFavorites : Event()
    }

    data class State(
        val charactersFavorites: List<Character>,
        override val stateRequest: StateRequest
    ) : UiState
}