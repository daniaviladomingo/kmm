package daniel.avila.ricknmortykmm.shared

import daniel.avila.ricknmortykmm.shared.data_remote.RemoteDataImp
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import daniel.avila.ricknmortykmm.shared.repository.model.mapper.ApiCharacterMapper
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

val httpClient = HttpClient {
    install(JsonFeature) {
        val json = Json { ignoreUnknownKeys = true }
        serializer = KotlinxSerializer(json)
    }
}

val dataRemote: IRemoteData = RemoteDataImp(httpClient)

val apiCharacterMapper: ApiCharacterMapper = ApiCharacterMapper()


