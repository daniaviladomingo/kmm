package daniel.avila.rnm.kmm.presentation.ui.features.character_detail

import cafe.adriel.voyager.core.model.coroutineScope
import daniel.avila.rnm.kmm.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.rnm.kmm.domain.interactors.GetCharacterUseCase
import daniel.avila.rnm.kmm.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.rnm.kmm.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.presentation.mvi.BaseViewModel
import daniel.avila.rnm.kmm.presentation.mvi.BasicUiState
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase,
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase,
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase,
    private val characterId: Int,
) : BaseViewModel<CharacterDetailContract.Event, CharacterDetailContract.State, CharacterDetailContract.Effect>() {

    private lateinit var character: Character

    init {
        getCharacter(characterId)
    }

    override fun createInitialState(): CharacterDetailContract.State =
        CharacterDetailContract.State(
            character = BasicUiState.Idle,
            isFavorite = false,
        )

    override fun handleEvent(event: CharacterDetailContract.Event) {
        when (event) {
            CharacterDetailContract.Event.OnFavoriteClick -> {
                if (currentState.isFavorite) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }
            }

            CharacterDetailContract.Event.OnTryCheckAgainClick -> getCharacter(characterId)
            CharacterDetailContract.Event.OnBackPressed -> setEffect { CharacterDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(characterId: Int) {
        setState { copy(character = BasicUiState.Loading) }
        coroutineScope.launch {
            getCharacterUseCase(characterId)
                .onSuccess {
                    setState { copy(character = BasicUiState.Success(it)) }
                    character = it
                    checkIfIsFavorite(it.id)
                }.onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        coroutineScope.launch {
            isCharacterFavoriteUseCase(idCharacter)
                .onSuccess { setState { copy(isFavorite = it) } }
                .onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }

    private fun addToFavorite() {
        coroutineScope.launch {
            addCharacterToFavoritesUseCase(character)
                .onSuccess {
                    setState { copy(isFavorite = true) }
                    setEffect { CharacterDetailContract.Effect.CharacterAdded }
                }.onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }

    private fun removeFromFavorite() {
        coroutineScope.launch {
            removeCharacterFromFavoritesUseCase(character.id)
                .onSuccess {
                    setState { copy(isFavorite = false) }
                    setEffect { CharacterDetailContract.Effect.CharacterRemoved }
                }.onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }
}