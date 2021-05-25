package daniel.avila.ricknmortykmm.androidApp

import android.app.Application
import daniel.avila.ricknmortykmm.androidApp.di.*
import daniel.avila.ricknmortykmm.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class KmmApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@KmmApplication)
            modules(mapperModule)
        }
    }
}