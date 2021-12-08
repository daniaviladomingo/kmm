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

    override fun getCharacters(): Flow<List<Character>> = flow {
        emit(remoteData.getCharactersFromApi().results.map { apiCharacterMapper.map(it) })
    }

    override fun getCharactersFavorites(): Flow<List<Character>> = flow {
        emit(cacheData.getAllCharacterFavorites())
    }

    override fun getCharacter(id: Int): Flow<Character> = flow {
        emit(apiCharacterMapper.map(remoteData.getCharacterFromApi(id)))
    }

    override fun addCharacterToFavorites(character: Character): Flow<Unit> = flow {
        cacheData.addCharacterToFavorite(character)
        emit(Unit)
    }

    override fun removeCharacterFromFavorite(idCharacter: Int): Flow<Unit> = flow {
        cacheData.removeCharacterFromFavorite(idCharacter)
        emit(Unit)
    }

    override fun isCharacterFavorite(idCharacter: Int): Flow<Boolean> = flow {
        emit(cacheData.isCharacterFavorite(idCharacter))
    }
}