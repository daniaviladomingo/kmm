package daniel.avila.ricknmortykmm.shared.data_cache

import com.squareup.sqldelight.ColumnAdapter
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.AppDatabase
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.datacache.sqldelight.CharacterFavorite
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Gender
import daniel.avila.ricknmortykmm.shared.domain.model.Gender.*
import daniel.avila.ricknmortykmm.shared.domain.model.Status
import daniel.avila.ricknmortykmm.shared.domain.model.Status.*
import daniel.avila.ricknmortykmm.shared.domain.model.Status.UNKNOWN
import daniel.avila.ricknmortykmm.shared.repository.ICacheData

class CacheDataImp(
    databaseDriverFactory: DatabaseDriverFactory
) : ICacheData {
    private val statusAdapter = object : ColumnAdapter<Status, String> {
        override fun decode(databaseValue: String): Status = when (databaseValue) {
            "Alive" -> ALIVE
            "Dead" -> DEAD
            else -> UNKNOWN
        }

        override fun encode(value: Status): String = when (value) {
            ALIVE -> "Alive"
            DEAD -> "Dead"
            UNKNOWN -> "Unknown"
        }
    }

    private val genderAdapter = object : ColumnAdapter<Gender, String> {
        override fun decode(databaseValue: String): Gender = when (databaseValue) {
            "Male" -> MALE
            "Female" -> FEMALE
            "Genderless" -> GENDERLESS
            else -> Gender.UNKNOWN
        }

        override fun encode(value: Gender): String = when (value) {
            MALE -> "Male"
            FEMALE -> "Female"
            GENDERLESS -> "Genderless"
            Gender.UNKNOWN -> "Unknown"
        }
    }

    private val database =
        AppDatabase.invoke(
            databaseDriverFactory.createDriver(),
            CharacterFavorite.Adapter(statusAdapter, genderAdapter)
        )
    private val dbQuery = database.appDatabaseQueries

    override fun addCharacterToFavorite(character: Character) {
        dbQuery.transaction {
            dbQuery.insertCharacterFavorite(
                character.id.toLong(),
                character.name,
                character.status,
                character.species,
                character.gender,
                character.origin,
                character.location,
                character.image
            )
        }
    }

    override fun removeCharacterFromFavorite(idCharacter: Int) {
        dbQuery.transaction {
            dbQuery.removeCharacterFavorite(idCharacter.toLong())
        }
    }

    override fun getAllCharacterFavorites(): List<Character> =
        dbQuery.selectAllCharacterFavorite(::mapFavorite).executeAsList()

    override fun isCharacterFavorite(idCharacter: Int): Boolean =
        dbQuery.selectCharacterFavoriteById(idCharacter.toLong()).executeAsOne()

    private fun mapFavorite(
        id: Long,
        name: String,
        status: Status,
        species: String,
        gender: Gender,
        origin: String,
        location: String,
        image: String
    ): Character = Character(id.toInt(), name, status, species, gender, origin, location, image)
}