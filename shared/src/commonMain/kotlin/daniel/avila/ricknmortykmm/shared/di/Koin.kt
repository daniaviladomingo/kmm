package daniel.avila.ricknmortykmm.shared.di

import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_remote.RemoteDataImp
import daniel.avila.ricknmortykmm.shared.data_remote.model.mapper.ApiCharacterMapper
import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharacterUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.repository.ICacheData
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            mapperModule,
            dispatcherModule,
            useCasesModule,
            platformModule()
        )
    }

// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get(), get()) }
    single<ICacheData> { CacheDataImp(get()) }
    single<IRemoteData> { RemoteDataImp(get(), get(), get()) }

    single {
        HttpClient {
            install(JsonFeature) {
                val json = Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
            }
        }
    }

    single { "https://rickandmortyapi.com/" }
}

val useCasesModule: Module = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharactersFavoritesUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    factory { AddCharacterToFavoritesUseCase(get()) }
    factory { RemoveCharacterFromFavoritesUseCase(get()) }
    factory { IsCharacterFavoriteUseCase(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val mapperModule = module {
    factory { ApiCharacterMapper() }
}


