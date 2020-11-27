package daniel.avila.ricknmortykmm.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import daniel.avila.ricknmortykmm.shared.Greeting
import android.widget.TextView
import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.AppDatabase
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.data_remote.RemoteDataImp
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataRemote = RemoteDataImp(HttpClient {
            install(JsonFeature) {
                val json = Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
            }
        })
        val dataCache = CacheDataImp(AppDatabase.invoke(DatabaseDriverFactory(this).createDriver()))

        val repository = RepositoryImp(dataCache, dataRemote)

        MainScope().launch(Dispatchers.IO) {

        }

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
