package daniel.avila.ricknmortykmm.shared.features

import daniel.avila.ricknmortykmm.shared.base.IBasePresenter

interface ICharactersPresenter<View: ICharactersView>: IBasePresenter<View> {
    fun loadCharacters()
}