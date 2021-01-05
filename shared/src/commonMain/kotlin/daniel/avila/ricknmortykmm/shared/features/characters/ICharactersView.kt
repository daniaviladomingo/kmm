package daniel.avila.ricknmortykmm.shared.features.characters

import daniel.avila.ricknmortykmm.shared.base.IBaseView
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharactersView: IBaseView {
    fun displayCharacters(characters: List<Character>)
}