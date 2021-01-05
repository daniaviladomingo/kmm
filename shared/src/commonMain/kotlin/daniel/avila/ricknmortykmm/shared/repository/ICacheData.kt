package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface ICacheData {
    fun addCharacterToFavorite(character: Character)
    fun removeCharacterFromFavorite(idCharacter: Int)
    fun getAllCharacterFavorites(): List<Character>
    fun isCharacterFavorite(idCharacter: Int): Boolean
}