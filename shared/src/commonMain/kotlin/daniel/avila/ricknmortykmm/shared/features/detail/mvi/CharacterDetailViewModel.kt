package daniel.avila.ricknmortykmm.shared.features.detail.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import org.koin.core.component.inject

open class CharacterDetailViewModel :
    BaseViewModel<CharacterDetailContract.Event, CharacterDetailContract.State, CharacterDetailContract.Effect>() {
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase by inject()
    private val addCharacterToFavoritesUseCase: AddCharacterToFavoritesUseCase by inject()
    private val removeCharacterFromFavoritesUseCase: RemoveCharacterFromFavoritesUseCase by inject()

    override fun createInitialState(): CharacterDetailContract.State =
        CharacterDetailContract.State(BasicUiState.None)

    override fun handleEvent(event: CharacterDetailContract.Event) {
        when (event) {
            is CharacterDetailContract.Event.CheckIfIsFavorite -> checkIfIsFavorite(event.idCharacter)
            is CharacterDetailContract.Event.OnAddCharacterToFavorite -> addToFavorite(event.character)
            is CharacterDetailContract.Event.RemoveCharacterToFavorite -> removeFromFavorite(event.idCharacter)
        }
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        setState { copy(isFavorite = BasicUiState.Loading) }
        launch(isCharacterFavoriteUseCase.execute(idCharacter), { data ->
            setState { copy(isFavorite = BasicUiState.Success(data)) }
        }, {
            setState { copy(isFavorite = BasicUiState.Error()) }
        })
    }

    private fun addToFavorite(character: Character) {
        launch(addCharacterToFavoritesUseCase.execute(character), {
            setEffect { CharacterDetailContract.Effect.CharacterAdded }
        })
    }

    private fun removeFromFavorite(idCharacter: Int) {
        launch(removeCharacterFromFavoritesUseCase.execute(idCharacter), {
            setEffect { CharacterDetailContract.Effect.CharacterRemoved }
        })
    }
}