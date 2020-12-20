package daniel.avila.ricknmortykmm.shared.features

import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.domain.IExecutor

class CharactersPresenter(
    private val view: ICharactersView,
    private val executor: IExecutor
): BasePresenter<ICharactersView>(
    view,
    executor
) {

}
