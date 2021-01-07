package daniel.avila.ricknmortykmm.shared.di

import daniel.avila.ricknmortykmm.shared.data_remote.RemoteDataImp
import daniel.avila.ricknmortykmm.shared.domain.Executor
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import daniel.avila.ricknmortykmm.shared.repository.model.mapper.ApiCharacterMapper
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

const val END_POINT = "https://rickandmortyapi.com/"

val httpClient = HttpClient {
    install(JsonFeature) {
        val json = Json { ignoreUnknownKeys = true }
        serializer = KotlinxSerializer(json)
    }
}

val dataRemote: IRemoteData = RemoteDataImp(END_POINT, httpClient)

val apiCharacterMapper: ApiCharacterMapper = ApiCharacterMapper()

val executor: Executor = Executor()

/* USE CASES */



/* PRESENTERS */


