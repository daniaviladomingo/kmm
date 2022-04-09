package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.repository.model.mapper.ApiCharacterMapper
import kotlinx.coroutines.flow.Flow

class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData,
    private val apiCharacterMapper: ApiCharacterMapper
) : IRepository {

    override suspend fun getCharacters(): List<Character> =
        remoteData.getCharactersFromApi().results.map { apiCharacterMapper.map(it) }

    override fun getCharactersFavorites(): Flow<List<Character>> =
        cacheData.getAllCharacterFavorites()

    override suspend fun getCharacter(id: Int): Character =
        apiCharacterMapper.map(remoteData.getCharacterFromApi(id))

    override fun addCharacterToFavorites(character: Character) =
        cacheData.addCharacterToFavorite(character)

    override fun removeCharacterFromFavorite(idCharacter: Int) =
        cacheData.removeCharacterFromFavorite(idCharacter)

    override fun isCharacterFavorite(idCharacter: Int): Boolean =
        cacheData.isCharacterFavorite(idCharacter)
}