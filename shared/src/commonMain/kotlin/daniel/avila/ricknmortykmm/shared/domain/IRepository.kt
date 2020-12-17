package daniel.avila.ricknmortykmm.shared.domain

import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getCharacters(): Flow<List<Character>>
//    suspend fun getCharactersFavorites(): List<Character>
//    suspend fun addCharacterToFavorites(character: Character)
//    suspend fun removeCharacterFromFavorite(idCharacter: Int)
//    suspend fun isCharacterFavorite(idCharacter: Int)
}