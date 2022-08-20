package daniel.avila.ricknmortykmm.shared.features.characters.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.core.Resource
import org.koin.core.component.inject

open class CharactersViewModel :
    BaseViewModel<CharactersContract.Event, CharactersContract.State, UiEffect>() {
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    init {
        getCharacters()
    }

    override fun createInitialState(): CharactersContract.State =
        CharactersContract.State(characters = BasicUiState.Idle)

    override fun handleEvent(event: CharactersContract.Event) {
        when (event) {
            CharactersContract.Event.OnGetCharacters -> getCharacters()
        }
    }

    private fun getCharacters() {
        setState { copy(characters = BasicUiState.Loading) }
        collect(getCharactersUseCase()) { resourceCharacters ->
            when (resourceCharacters) {
                is Resource.Error -> setState { copy(characters = BasicUiState.Error()) }
                is Resource.Success -> setState {
                    copy(
                        characters = if (resourceCharacters.data.isEmpty())
                            BasicUiState.Empty
                        else
                            BasicUiState.Success(resourceCharacters.data)
                    )
                }
            }
        }
    }
}