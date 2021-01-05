package daniel.avila.ricknmortykmm.shared.features.favorites

import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharactersFavoritePresenter<View: ICharactersFavoritesView>: IBasePresenter<View> {
    fun loadCharacters()
    fun onCharacterClick(character: Character)
}