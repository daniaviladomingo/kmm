package daniel.avila.ricknmortykmm.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import daniel.avila.ricknmortykmm.shared.Greeting
import android.widget.TextView

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val dataCache = CacheDataImp(AppDatabase.invoke(DatabaseDriverFactory(this).createDriver()))
//
//        val repository = RepositoryImp(dataCache, dataRemote, apiCharacterMapper)
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val characters = withContext(Dispatchers.IO){
//                repository.getCharacters()
//            }
//            characters.forEach {
//                Log.d("aaa", "$it")
//            }
//        }

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
