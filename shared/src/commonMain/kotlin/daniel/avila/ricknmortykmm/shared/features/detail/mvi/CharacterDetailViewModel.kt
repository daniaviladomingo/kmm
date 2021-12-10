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
        CharacterDetailContract.State(BasicUiState.None, false)

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
        launch(getCharacterUseCase.execute(characterId), { character ->
            setState { copy(character = BasicUiState.Success(character)) }
            this.character = character
            checkIfIsFavorite(character.id)
        }, {
            setState { copy(character = BasicUiState.Error()) }
        })
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        launch(isCharacterFavoriteUseCase.execute(idCharacter), { isFavorite ->
            setState { copy(isFavorite = isFavorite) }
        }, {
            setState { copy(character = BasicUiState.Error()) }
        })
    }

    private fun addToFavorite() {
        launch(addCharacterToFavoritesUseCase.execute(character), {
            setState { copy(isFavorite = true) }
            setEffect { CharacterDetailContract.Effect.CharacterAdded }
        })
    }

    private fun removeFromFavorite() {
        launch(removeCharacterFromFavoritesUseCase.execute(character.id), {
            setState { copy(isFavorite = false) }
            setEffect { CharacterDetailContract.Effect.CharacterRemoved }
        })
    }
}