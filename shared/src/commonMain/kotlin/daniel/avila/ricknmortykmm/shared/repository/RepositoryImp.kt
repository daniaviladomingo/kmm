package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.repository.model.mapper.ApiCharacterMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData,
    private val apiCharacterMapper: ApiCharacterMapper
) : IRepository {

    override suspend fun getCharacters(): Flow<List<Character>> = flow {
        emit(remoteData.getCharactersFromApi().results.map { apiCharacterMapper.map(it) })
    }

    override suspend fun getCharactersFavorites(): Flow<List<Character>> = flow {
        emit(cacheData.getAllCharacterFavorites())
    }

    override suspend fun addCharacterToFavorites(character: Character): Flow<Unit> = flow {
        cacheData.addCharacterToFavorite(character)
        emit(Unit)
    }

    override suspend fun removeCharacterFromFavorite(idCharacter: Int): Flow<Unit> = flow {
        cacheData.removeCharacterFromFavorite(idCharacter)
        emit(Unit)
    }

    override suspend fun isCharacterFavorite(idCharacter: Int): Flow<Boolean> = flow {
        emit(cacheData.isCharacterFavorite(idCharacter))
    }
}