package daniel.avila.ricknmortykmm.shared.features.characters

import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharactersPresenter<View: ICharactersView>: IBasePresenter<View> {
    fun loadCharacters()
    fun onCharacterClick(character: Character)
    fun onFavoritesClick()
}