package daniel.avila.rnm.kmm.presentation.ui.features.character_detail

import cafe.adriel.voyager.core.model.coroutineScope
import daniel.avila.rnm.kmm.domain.interactors.GetCharacterUseCase
import daniel.avila.rnm.kmm.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.rnm.kmm.domain.interactors.SwitchCharacterFavoriteUseCase
import daniel.avila.rnm.kmm.presentation.mvi.BaseViewModel
import daniel.avila.rnm.kmm.presentation.mvi.BasicUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase,
    private val switchCharacterFavoriteUseCase: SwitchCharacterFavoriteUseCase,
    private val characterId: Int,
) : BaseViewModel<CharacterDetailContract.Event, CharacterDetailContract.State, CharacterDetailContract.Effect>() {

    init {
        getCharacter(characterId)
        checkIfIsFavorite(characterId)
    }

    override fun createInitialState(): CharacterDetailContract.State =
        CharacterDetailContract.State(
            character = BasicUiState.Idle,
            isFavorite = BasicUiState.Idle,
        )

    override fun handleEvent(event: CharacterDetailContract.Event) {
        when (event) {
            CharacterDetailContract.Event.OnFavoriteClick -> switchCharacterFavorite(characterId)
            CharacterDetailContract.Event.OnTryCheckAgainClick -> getCharacter(characterId)
            CharacterDetailContract.Event.OnBackPressed -> setEffect { CharacterDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(characterId: Int) {
        setState { copy(character = BasicUiState.Loading) }
        coroutineScope.launch {
            getCharacterUseCase(characterId)
                .onSuccess { setState { copy(character = BasicUiState.Success(it)) } }
                .onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        setState { copy(isFavorite = BasicUiState.Loading) }
        coroutineScope.launch {
            isCharacterFavoriteUseCase(idCharacter)
                .onSuccess { setState { copy(isFavorite = BasicUiState.Success(it)) } }
                .onFailure { setState { copy(isFavorite = BasicUiState.Error()) } }
        }
    }

    private fun switchCharacterFavorite(idCharacter: Int) {
        setState { copy(isFavorite = BasicUiState.Loading) }
        coroutineScope.launch {
            delay(2000)
            switchCharacterFavoriteUseCase(idCharacter)
                .onSuccess {
                    setState { copy(isFavorite = BasicUiState.Success(it)) }
                    setEffect { CharacterDetailContract.Effect.CharacterAdded }
                }.onFailure { setState { copy(isFavorite = BasicUiState.Error()) } }
        }
    }
}