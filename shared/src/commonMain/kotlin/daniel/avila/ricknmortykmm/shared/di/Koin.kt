package daniel.avila.ricknmortykmm.shared.di

import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_remote.RemoteDataImp
import daniel.avila.ricknmortykmm.shared.domain.IRepository
import daniel.avila.ricknmortykmm.shared.domain.interactors.*
import daniel.avila.ricknmortykmm.shared.features.characters.CharactersPresenter
import daniel.avila.ricknmortykmm.shared.features.characters.ICharactersPresenter
import daniel.avila.ricknmortykmm.shared.features.characters.ICharactersView
import daniel.avila.ricknmortykmm.shared.features.characters.INavigatorCharacters
import daniel.avila.ricknmortykmm.shared.features.detail.CharacterDetailPresenter
import daniel.avila.ricknmortykmm.shared.features.detail.ICharacterDetailPresenter
import daniel.avila.ricknmortykmm.shared.features.detail.ICharacterDetailView
import daniel.avila.ricknmortykmm.shared.features.favorites.CharactersFavoritesPresenter
import daniel.avila.ricknmortykmm.shared.features.favorites.ICharactersFavoritePresenter
import daniel.avila.ricknmortykmm.shared.features.favorites.ICharactersFavoritesView
import daniel.avila.ricknmortykmm.shared.features.favorites.INavigatorCharactersFavorites
import daniel.avila.ricknmortykmm.shared.repository.ICacheData
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp
import daniel.avila.ricknmortykmm.shared.repository.model.mapper.ApiCharacterMapper
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            presenterModule,
            useCaseModule,
            repositoryModule,
            mapperModule,
            platformModule()
        )
    }

// IOS
fun initKoin() = initKoin {}

val presenterModule = module {
    factory<ICharactersPresenter<ICharactersView>> { (navigator: INavigatorCharacters) ->
        CharactersPresenter(navigator)
    }

    factory<ICharactersFavoritePresenter<ICharactersFavoritesView>> { (navigator: INavigatorCharactersFavorites) ->
        CharactersFavoritesPresenter(navigator)
    }

    factory<ICharacterDetailPresenter<ICharacterDetailView>> {
        CharacterDetailPresenter()
    }
}

val useCaseModule = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharactersFavoritesUseCase(get()) }
    factory { AddCharacterToFavoritesUseCase(get()) }
    factory { RemoveCharacterFromFavoritesUseCase(get()) }
    factory { IsCharacterFavoriteUseCase(get()) }
}

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get(), get(), get()) }
    single<ICacheData> { CacheDataImp(get()) }
    single<IRemoteData> { RemoteDataImp(get(), get()) }

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

val mapperModule = module {
    factory { ApiCharacterMapper() }
}


