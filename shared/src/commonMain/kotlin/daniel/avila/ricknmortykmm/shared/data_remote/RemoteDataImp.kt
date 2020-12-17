package daniel.avila.ricknmortykmm.shared.data_remote

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharactersResponse
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import io.ktor.client.request.*
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.*
import kotlinx.serialization.json.Json

class RemoteDataImp(
    private val endPoint: String,
    private val httpClient: HttpClient
) : IRemoteData {
    override suspend fun getCharactersFromApi(): ApiCharactersResponse =
        httpClient.get { apiUrl("/api/character") }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}