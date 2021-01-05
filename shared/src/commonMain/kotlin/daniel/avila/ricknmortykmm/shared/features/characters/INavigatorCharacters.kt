package daniel.avila.ricknmortykmm.shared.features.characters

import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface INavigatorCharacters {
    fun navigateToDetail(character: Character)
    fun navigateToFavorites()

}