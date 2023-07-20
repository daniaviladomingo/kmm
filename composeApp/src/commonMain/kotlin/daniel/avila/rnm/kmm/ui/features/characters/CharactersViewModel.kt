package daniel.avila.rnm.kmm.ui.features.characters

import cafe.adriel.voyager.core.model.coroutineScope
import daniel.avila.rnm.kmm.domain.interactors.GetCharactersUseCase
import daniel.avila.rnm.kmm.ui.mvi.BaseViewModel
import daniel.avila.rnm.kmm.ui.mvi.BasicUiState
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel<CharactersContract.Event, CharactersContract.State, CharactersContract.Effect>() {

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
        coroutineScope.launch {
            getCharactersUseCase(Unit)
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
