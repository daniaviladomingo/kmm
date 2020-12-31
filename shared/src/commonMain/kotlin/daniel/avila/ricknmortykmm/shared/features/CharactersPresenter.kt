package daniel.avila.ricknmortykmm.shared.features

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.Executor
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharacterUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersPresenter(
    private val getCharacterUseCase: GetCharacterUseCase,
    executor: Executor
) : BasePresenter<ICharactersView>(
    executor
), ICharactersPresenter {
    override fun loadCharacters() {
        launch {
            getCharacterUseCase.execute().collect { characters ->
                view.displayCharacters(characters)
            }
        }
    }
}
