package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.model.Character


class RepositoryImp(
    private val cacheData: ICacheData,
    private val remoteData: IRemoteData
): IRepository {
    override suspend fun getCharacters(): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersFavorites(): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun addCharacterToFavorites(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun removeCharacterFromFavorite(idCharacter: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun isCharacterFavorite(idCharacter: Int) {
        TODO("Not yet implemented")
    }
}