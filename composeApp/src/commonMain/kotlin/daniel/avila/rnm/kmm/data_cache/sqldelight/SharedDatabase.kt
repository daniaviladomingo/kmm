package daniel.avila.rnm.kmm.data_cache.sqldelight

import app.cash.sqldelight.ColumnAdapter
import daniel.avila.rnm.kmm.datacache.sqldelight.CharacterFavorite
import daniel.avila.rnm.kmm.domain.model.Gender
import daniel.avila.rnm.kmm.domain.model.Status

class SharedDatabase(
    private val driverProvider: DatabaseDriverFactory,
) {
    private var database: AppDatabase? = null

    private val statusAdapter = object : ColumnAdapter<Status, String> {
        override fun decode(databaseValue: String): Status = when (databaseValue) {
            "Alive" -> Status.ALIVE
            "Dead" -> Status.DEAD
            else -> Status.UNKNOWN
        }

        override fun encode(value: Status): String = when (value) {
            Status.ALIVE -> "Alive"
            Status.DEAD -> "Dead"
            Status.UNKNOWN -> "Unknown"
        }
    }

    private val genderAdapter = object : ColumnAdapter<Gender, String> {
        override fun decode(databaseValue: String): Gender = when (databaseValue) {
            "Male" -> Gender.MALE
            "Female" -> Gender.FEMALE
            "Genderless" -> Gender.GENDERLESS
            else -> Gender.UNKNOWN
        }

        override fun encode(value: Gender): String = when (value) {
            Gender.MALE -> "Male"
            Gender.FEMALE -> "Female"
            Gender.GENDERLESS -> "Genderless"
            Gender.UNKNOWN -> "Unknown"
        }
    }

    private suspend fun initDatabase() {
        if (database == null) {
            database = AppDatabase.invoke(
                driverProvider.createDriver(),
                CharacterFavorite.Adapter(statusAdapter, genderAdapter)
            )
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }
}