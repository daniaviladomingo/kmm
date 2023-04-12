package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharacterUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import org.koin.core.component.inject

open class CharacterDetailViewModel :
    BaseViewModel<CharacterDetailContract.Event, CharacterDetailContract.State, CharacterDetailContract.Effect>() {
    private val getCharacterUseCase: GetCharacterUseCase by inject()
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase by inject()
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase by inject()
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase by inject()

    private var characterId: Int? = null
    private lateinit var character: Character

    override fun createInitialState(): CharacterDetailContract.State =
        CharacterDetailContract.State(
            character = BasicUiState.Idle,
            isFavorite = false,
        )

    override fun handleEvent(event: CharacterDetailContract.Event) {
        when (event) {
            is CharacterDetailContract.Event.GetCharacter -> getCharacter(event.idCharacter)
            CharacterDetailContract.Event.OnAddCharacterToFavoriteClick -> addToFavorite()
            CharacterDetailContract.Event.OnRemoveCharacterFromFavoriteClick -> removeFromFavorite()
            CharacterDetailContract.Event.OnTryCheckAgainClick -> characterId?.let { getCharacter(it) }
            CharacterDetailContract.Event.OnBackPressed -> setEffect { CharacterDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(characterId: Int) {
        this.characterId = characterId
        setState { copy(character = BasicUiState.Loading) }
        collect(getCharacterUseCase(characterId)) { resourceCharacter ->
            resourceCharacter
                .onSuccess {
                    setState { copy(character = BasicUiState.Success(it)) }
                    this.character = it
                    checkIfIsFavorite(it.id)
                }
                .onFailure { setState { copy(character = BasicUiState.Error()) } }

        }
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        collect(isCharacterFavoriteUseCase(idCharacter)) { resourceIsFavorite ->
            resourceIsFavorite
                .onSuccess { setState { copy(isFavorite = it) } }
                .onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }

    private fun addToFavorite() {
        collect(addCharacterToFavoritesUseCase(character)) { resource ->
            resource
                .onSuccess {
                    setState { copy(isFavorite = true) }
                    setEffect { CharacterDetailContract.Effect.CharacterAdded }
                }
                .onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }

    private fun removeFromFavorite() {
        collect(removeCharacterFromFavoritesUseCase(character.id)) { resource ->
            resource
                .onSuccess {
                    setState { copy(isFavorite = false) }
                    setEffect { CharacterDetailContract.Effect.CharacterRemoved }
                }
                .onFailure { setState { copy(character = BasicUiState.Error()) } }
        }
    }
}