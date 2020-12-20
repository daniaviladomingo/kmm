package daniel.avila.ricknmortykmm.shared.features

import daniel.avila.ricknmortykmm.shared.base.IViewResourceState
import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICharactersView: IViewResourceState {
    fun showCharacters(characters: List<Character>)
}