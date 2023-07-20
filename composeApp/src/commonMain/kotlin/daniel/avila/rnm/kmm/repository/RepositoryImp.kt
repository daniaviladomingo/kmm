package daniel.avila.rnm.kmm.repository

import daniel.avila.rnm.kmm.domain.IRepository
import daniel.avila.rnm.kmm.domain.model.Character
import kotlinx.coroutines.flow.Flow

class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData,
) : IRepository {

    override suspend fun getCharacters(): List<Character> =
        remoteData.getCharactersFromApi()

    override suspend fun getCharactersFavorites(): Flow<List<Character>> =
        cacheData.getAllCharacterFavorites()

    override suspend fun getCharacter(id: Int): Character =
        remoteData.getCharacterFromApi(id)

    override suspend fun addCharacterToFavorites(character: Character) =
        cacheData.addCharacterToFavorite(character)

    override suspend fun removeCharacterFromFavorite(idCharacter: Int) =
        cacheData.removeCharacterFromFavorite(idCharacter)

    override suspend fun isCharacterFavorite(idCharacter: Int): Boolean =
        cacheData.isCharacterFavorite(idCharacter)
}