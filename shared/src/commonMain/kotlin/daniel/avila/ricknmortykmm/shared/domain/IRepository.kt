package daniel.avila.ricknmortykmm.shared.domain

import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getCharacters(): Flow<List<Character>>
    suspend fun getCharactersFavorites(): Flow<List<Character>>
    suspend fun addCharacterToFavorites(character: Character): Flow<Unit>
    suspend fun removeCharacterFromFavorite(idCharacter: Int): Flow<Unit>
    suspend fun isCharacterFavorite(idCharacter: Int): Flow<Boolean>
}