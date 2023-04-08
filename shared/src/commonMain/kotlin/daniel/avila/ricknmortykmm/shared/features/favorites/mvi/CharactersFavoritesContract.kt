package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.*
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersContract
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract

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