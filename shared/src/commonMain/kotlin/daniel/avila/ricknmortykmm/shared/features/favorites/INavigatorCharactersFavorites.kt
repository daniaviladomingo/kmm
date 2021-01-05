package daniel.avila.ricknmortykmm.shared.features.favorites

import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface INavigatorCharactersFavorites {
    fun navigateToDetail(character: Character)
}