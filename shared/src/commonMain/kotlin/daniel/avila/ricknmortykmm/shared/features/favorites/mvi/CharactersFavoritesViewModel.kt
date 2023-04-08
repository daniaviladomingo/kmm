package daniel.avila.ricknmortykmm.shared.features.favorites.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import org.koin.core.component.inject

open class CharactersFavoritesViewModel :
    BaseViewModel<CharactersFavoritesContract.Event, CharactersFavoritesContract.State, CharactersFavoritesContract.Effect>() {
    private val getCharactersFavoritesUseCase: GetCharactersFavoritesUseCase by inject()

    init {
        getCharactersFavorites()
    }

    override fun createInitialState(): CharactersFavoritesContract.State =
        CharactersFavoritesContract.State(
            charactersFavorites = BasicUiState.Idle
        )

    override fun handleEvent(event: CharactersFavoritesContract.Event) {
        when (event) {
            CharactersFavoritesContract.Event.OnTryCheckAgainClick -> getCharactersFavorites()
            is CharactersFavoritesContract.Event.OnCharacterClick ->
                setEffect { CharactersFavoritesContract.Effect.NavigateToDetailCharacter(event.idCharacter) }
            CharactersFavoritesContract.Event.OnBackPressed ->
                setEffect { CharactersFavoritesContract.Effect.BackNavigation }
        }
    }

    private fun getCharactersFavorites() {
        setState { copy(charactersFavorites = BasicUiState.Loading) }
        collect(getCharactersFavoritesUseCase()) { resourceFavorites ->
            when (resourceFavorites) {
                is Resource.Error -> setState { copy(charactersFavorites = BasicUiState.Error()) }
                is Resource.Success -> setState {
                    copy(
                        charactersFavorites =
                        if (resourceFavorites.data.isEmpty())
                            BasicUiState.Empty
                        else
                            BasicUiState.Success(resourceFavorites.data)
                    )
                }
            }
        }
    }
}