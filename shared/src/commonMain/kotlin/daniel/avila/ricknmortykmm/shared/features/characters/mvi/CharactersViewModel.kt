package daniel.avila.ricknmortykmm.shared.features.characters.mvi

import daniel.avila.ricknmortykmm.shared.base.mvi.BaseViewModel
import daniel.avila.ricknmortykmm.shared.base.mvi.StateRequest
import daniel.avila.ricknmortykmm.shared.base.mvi.UiEffect
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import org.koin.core.component.inject

open class CharactersViewModel :
    BaseViewModel<CharactersContract.Event, CharactersContract.State, UiEffect>() {
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    init {
        getCharacters()
    }

    override fun createInitialState(): CharactersContract.State =
        CharactersContract.State(characters = emptyList(), stateRequest = StateRequest.Idle)

    override fun handleEvent(event: CharactersContract.Event) {
        when (event) {
            CharactersContract.Event.OnGetCharacters -> getCharacters()
        }
    }

    private fun getCharacters() {
        setState { copy(stateRequest = StateRequest.Loading) }
        launch(getCharactersUseCase.execute(), { characters ->
            setState {
                copy(
                    characters = characters,
                    stateRequest = if (characters.isEmpty())
                        StateRequest.Empty()
                    else
                        StateRequest.Success
                )
            }
        }, {
            setState { copy(stateRequest = StateRequest.Error()) }
        })
    }
}