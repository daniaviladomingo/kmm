package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.*
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharacterDetailContract {
    sealed interface Event : UiEvent {
        data class GetCharacter(val idCharacter: Int) : Event
        object AddCharacterToFavorite : Event
        object RemoveCharacterToFavorite : Event
        object Retry : Event
    }

    data class State(
        val character: BasicUiState<Character>,
        val isFavorite: Boolean,
    ) : UiState

    sealed interface Effect : UiEffect {
        object CharacterAdded : Effect
        object CharacterRemoved : Effect
    }
}