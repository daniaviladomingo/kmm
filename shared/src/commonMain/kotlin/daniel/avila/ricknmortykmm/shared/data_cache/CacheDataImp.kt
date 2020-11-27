package daniel.avila.ricknmortykmm.shared.data_cache

import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.AppDatabase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.repository.ICacheData

class CacheDataImp(
    sqldelightDataBase: AppDatabase
) : ICacheData {
    private val dbQuery = sqldelightDataBase.appDatabaseQueries

    override fun addCharacterToFavorite(character: Character) {
        dbQuery.transaction {
            dbQuery.insertCharacterFavorite(character.id.toLong(), character.name, character.image)
        }
    }

    override fun removeCharacterFromFavorite(idCharacter: Int) {
        dbQuery.transaction {
            dbQuery.removeCharacterFavorite(idCharacter.toLong())
        }
    }

    override fun getAllCharacterFavorites(): List<Character> =
        dbQuery.selectAllCharacterFavorite(::mapFavorite).executeAsList()

    private fun mapFavorite(
        id: Long,
        name: String,
        image: String
    ): Character = Character(id.toInt(), name, image)
}