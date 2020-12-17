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
): IRepository {

    override suspend fun getCharacters(): Flow<List<Character>> = flow {
        emit(remoteData.getCharactersFromApi().results.map { apiCharacterMapper.map(it) })
    }

//    override suspend fun getCharactersFavorites(): List<Character> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun addCharacterToFavorites(character: Character) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun removeCharacterFromFavorite(idCharacter: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun isCharacterFavorite(idCharacter: Int) {
//        TODO("Not yet implemented")
//    }
}