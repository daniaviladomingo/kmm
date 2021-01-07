package daniel.avila.ricknmortykmm.androidApp

import android.app.Application
//import daniel.avila.ricknmortykmm.androidApp.di.*
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
//import org.koin.core.context.startKoin

class KmmApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@KmmApplication)
//            androidLogger()
//            modules(
//                presenterModule,
//                executorModule,
//                useCaseModule,
//                repositoryModule,
//                mapperModule,
//            )
//        }
    }
}