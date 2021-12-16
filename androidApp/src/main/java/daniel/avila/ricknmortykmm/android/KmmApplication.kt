package daniel.avila.ricknmortykmm.android

import android.app.Application
import daniel.avila.ricknmortykmm.android.di.viewModelModule
import daniel.avila.ricknmortykmm.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class KmmApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@KmmApplication)
            modules(
                viewModelModule
            )
        }
    }
}