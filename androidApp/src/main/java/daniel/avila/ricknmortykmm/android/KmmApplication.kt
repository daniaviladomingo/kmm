package daniel.avila.ricknmortykmm.android

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import daniel.avila.ricknmortykmm.android.di.viewModelModule
import daniel.avila.ricknmortykmm.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class KmmApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (isDebug()) Level.ERROR else Level.NONE)
            androidContext(this@KmmApplication)
            modules(
                viewModelModule
            )
        }
    }
}

fun Context.isDebug() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE