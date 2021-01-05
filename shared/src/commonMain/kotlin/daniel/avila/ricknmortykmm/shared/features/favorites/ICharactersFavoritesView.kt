package daniel.avila.ricknmortykmm.shared.features.favorites

import daniel.avila.ricknmortykmm.shared.base.IBaseView
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharactersFavoritesView: IBaseView {
    fun displayCharacters(characters: List<Character>)
}