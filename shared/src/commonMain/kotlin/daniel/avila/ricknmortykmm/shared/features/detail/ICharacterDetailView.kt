package daniel.avila.ricknmortykmm.shared.features.detail

import daniel.avila.ricknmortykmm.shared.base.IBaseView
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharacterDetailView : IBaseView {
    fun isFavorite(isFavorite: Boolean)
    fun addedFavorite()
    fun removedFavorite()
}