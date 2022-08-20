package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharacterUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
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
            CharacterDetailContract.Event.AddCharacterToFavorite -> addToFavorite()
            CharacterDetailContract.Event.RemoveCharacterToFavorite -> removeFromFavorite()
            CharacterDetailContract.Event.Retry -> characterId?.let { getCharacter(it) }
        }
    }

    private fun getCharacter(characterId: Int) {
        this.characterId = characterId
        setState { copy(character = BasicUiState.Loading) }
        collect(getCharacterUseCase(characterId)) { resourceCharacter ->
            when (resourceCharacter) {
                is Resource.Error -> setState { copy(character = BasicUiState.Error()) }
                is Resource.Success -> {
                    setState { copy(character = BasicUiState.Success(resourceCharacter.data)) }
                    this.character = resourceCharacter.data
                    checkIfIsFavorite(resourceCharacter.data.id)
                }
            }

        }
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        collect(isCharacterFavoriteUseCase(idCharacter)) { resourceIsFavorite ->
            when (resourceIsFavorite) {
                is Resource.Error -> setState { copy(character = BasicUiState.Error()) }
                is Resource.Success -> setState { copy(isFavorite = resourceIsFavorite.data) }
            }
        }
    }

    private fun addToFavorite() {
        collect(addCharacterToFavoritesUseCase(character)) { resource ->
            when (resource) {
                is Resource.Error -> setState { copy(character = BasicUiState.Error()) }
                is Resource.Success -> {
                    setState { copy(isFavorite = true) }
                    setEffect { CharacterDetailContract.Effect.CharacterAdded }
                }
            }
        }
    }

    private fun removeFromFavorite() {
        collect(removeCharacterFromFavoritesUseCase(character.id)) { resource ->
            when (resource) {
                is Resource.Error -> setState { copy(character = BasicUiState.Error()) }
                is Resource.Success -> {
                    setState { copy(isFavorite = false) }
                    setEffect { CharacterDetailContract.Effect.CharacterRemoved }
                }
            }
        }
    }
}