package daniel.avila.ricknmortykmm.shared.features.characters.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import org.koin.core.component.inject

open class CharactersViewModel :
    BaseViewModel<CharactersContract.Event, CharactersContract.State, CharactersContract.Effect>() {
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    init {
        getCharacters()
    }

    override fun createInitialState(): CharactersContract.State =
        CharactersContract.State(characters = BasicUiState.Idle)

    override fun handleEvent(event: CharactersContract.Event) {
        when (event) {
            CharactersContract.Event.OnTryCheckAgainClick -> getCharacters()
            is CharactersContract.Event.OnCharacterClick -> setEffect {
                CharactersContract.Effect.NavigateToDetailCharacter(
                    event.idCharacter
                )
            }
            CharactersContract.Event.OnFavoritesClick -> setEffect { CharactersContract.Effect.NavigateToFavorites }
        }
    }

    private fun getCharacters() {
        setState { copy(characters = BasicUiState.Loading) }
        collect(getCharactersUseCase()) { resourceCharacters ->
            resourceCharacters
                .onSuccess {
                    setState {
                        copy(
                            characters = if (it.isEmpty())
                                BasicUiState.Empty
                            else
                                BasicUiState.Success(it)
                        )
                    }
                }
                .onFailure { setState { copy(characters = BasicUiState.Error()) } }
        }
    }
}