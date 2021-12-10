package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEvent
import daniel.avila.ricknmortykmm.shared.base.mvi.UiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharacterDetailContract {
    sealed class Event : UiEvent {
        data class GetCharacter(val idCharacter: Int) : Event()
        object AddCharacterToFavorite : Event()
        object RemoveCharacterToFavorite : Event()
        object Retry : Event()
    }

    data class State(
        val character: BasicUiState<Character>,
        val isFavorite: Boolean,
//        val requestState: RequestState
    ) : UiState

    sealed class RequestState {
        object Loading : RequestState()
        data class Error(val message: String? = null) : RequestState()
        data class Empty(val message: String? = null) : RequestState()
    }

    sealed class Effect : UiEffect {
        object CharacterAdded : Effect()
        object CharacterRemoved : Effect()
    }
}