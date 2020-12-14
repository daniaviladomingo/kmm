package daniel.avila.ricknmortykmm.shared.domain

import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface IRepository {
    @Throws(Exception::class) suspend fun getCharacters(): List<Character>
//    suspend fun getCharactersFavorites(): List<Character>
//    suspend fun addCharacterToFavorites(character: Character)
//    suspend fun removeCharacterFromFavorite(idCharacter: Int)
//    suspend fun isCharacterFavorite(idCharacter: Int)
}