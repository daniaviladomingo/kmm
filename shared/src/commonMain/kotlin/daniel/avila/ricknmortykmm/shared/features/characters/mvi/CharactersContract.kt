package daniel.avila.ricknmortykmm.shared.features.characters.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.StateRequest
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEvent
import daniel.avila.ricknmortykmm.shared.base.mvi.UiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharactersContract {
    sealed class Event : UiEvent {
        object OnGetCharacters : Event()
    }

    data class State(
        val characters: List<Character>,
        override val stateRequest: StateRequest
    ) : UiState
}


