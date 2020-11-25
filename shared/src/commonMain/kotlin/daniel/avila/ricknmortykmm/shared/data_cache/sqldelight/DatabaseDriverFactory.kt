package daniel.avila.ricknmortykmm.shared.data_cache.sqldelight

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}