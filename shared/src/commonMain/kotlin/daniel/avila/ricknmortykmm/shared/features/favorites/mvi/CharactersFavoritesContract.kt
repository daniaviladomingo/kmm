package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEvent
import daniel.avila.ricknmortykmm.shared.base.mvi.UiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharactersFavoritesContract {
    sealed class Event : UiEvent {
        object OnGetCharactersFavorites : Event()
    }

    data class State(
        val charactersFavorites: BasicUiState<List<Character>>
    ) : UiState
}