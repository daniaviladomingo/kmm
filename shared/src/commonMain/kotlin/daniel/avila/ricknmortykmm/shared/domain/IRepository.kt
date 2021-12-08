package daniel.avila.ricknmortykmm.shared.domain

import daniel.avila.ricknmortykmm.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getCharacters(): Flow<List<Character>>
    fun getCharactersFavorites(): Flow<List<Character>>
    fun getCharacter(id: Int): Flow<Character>
    fun addCharacterToFavorites(character: Character): Flow<Unit>
    fun removeCharacterFromFavorite(idCharacter: Int): Flow<Unit>
    fun isCharacterFavorite(idCharacter: Int): Flow<Boolean>
}