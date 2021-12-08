package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEvent
import daniel.avila.ricknmortykmm.shared.base.mvi.UiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface CharacterDetailContract {
    sealed class Event : UiEvent {
        data class GetCharacter(val idCharacter: Int): Event()
        data class CheckIfIsFavorite(val idCharacter: Int) : Event()
        data class OnAddCharacterToFavorite(val character: Character) : Event()
        data class RemoveCharacterToFavorite(val idCharacter: Int) : Event()
    }

    data class State(
        val character: BasicUiState<Character>,
        val isFavorite: BasicUiState<Boolean>
    ) : UiState

    sealed class Effect : UiEffect {
        object CharacterAdded: Effect()
        object CharacterRemoved: Effect()
    }
}