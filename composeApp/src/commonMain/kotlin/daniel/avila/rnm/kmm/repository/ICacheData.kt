package daniel.avila.rnm.kmm.repository

import daniel.avila.rnm.kmm.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICacheData {
    suspend fun addCharacterToFavorite(character: Character)
    suspend fun removeCharacterFromFavorite(idCharacter: Int)
    suspend fun getAllCharacterFavorites(): Flow<List<Character>>
    suspend fun isCharacterFavorite(idCharacter: Int): Boolean
}