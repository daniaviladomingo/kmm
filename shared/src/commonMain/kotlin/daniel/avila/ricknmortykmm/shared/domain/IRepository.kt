package daniel.avila.ricknmortykmm.shared.domain

import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface IRepository {
    suspend fun getCharacters(): List<Character>
    fun getCharactersFavorites(): List<Character>
    suspend fun getCharacter(id: Int): Character
    fun addCharacterToFavorites(character: Character)
    fun removeCharacterFromFavorite(idCharacter: Int)
    fun isCharacterFavorite(idCharacter: Int): Boolean
}