package daniel.avila.ricknmortykmm.shared.domain

import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getCharacters(): List<Character>
    fun getCharactersFavorites(): Flow<List<Character>>
    suspend fun getCharacter(id: Int): Character
    fun addCharacterToFavorites(character: Character)
    fun removeCharacterFromFavorite(idCharacter: Int)
    fun isCharacterFavorite(idCharacter: Int): Boolean
}