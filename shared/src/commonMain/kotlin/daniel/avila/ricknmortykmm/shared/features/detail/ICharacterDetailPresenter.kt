package daniel.avila.ricknmortykmm.shared.features.detail

import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharacterDetailPresenter<View: ICharacterDetailView>: IBasePresenter<View> {
    fun isFavorite(idCharacter: Int)
    fun addCharacterToFavorite(character: Character)
    fun removeCharacterFromFavorite(id: Int)
}