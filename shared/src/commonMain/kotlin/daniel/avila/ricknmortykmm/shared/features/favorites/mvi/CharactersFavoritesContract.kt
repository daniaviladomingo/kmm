package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.*
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharactersFavoritesContract {
    sealed interface Event : UiEvent {
        object OnGetCharactersFavorites : Event
    }

    data class State(
        val charactersFavorites: BasicUiState<List<Character>>,
    ) : UiState
}