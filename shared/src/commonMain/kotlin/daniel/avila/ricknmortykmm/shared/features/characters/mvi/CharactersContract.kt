package daniel.avila.ricknmortykmm.shared.features.characters.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEvent
import daniel.avila.ricknmortykmm.shared.base.mvi.UiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharactersContract {
    sealed interface Event : UiEvent {
        object OnGetCharacters : Event
    }

    data class State(
        val characters: BasicUiState<List<Character>>
    ) : UiState
}


