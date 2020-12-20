package daniel.avila.ricknmortykmm.shared.features

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.Executor

class CharactersPresenter(
    private val view: ICharactersView,
    private val executor: Executor
): BasePresenter<ICharactersView>(
    view,
    executor
) {

}
