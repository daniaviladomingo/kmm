package daniel.avila.ricknmortykmm.shared.data_remote

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharacter
import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharactersResponse
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RemoteDataImp(
    private val endPoint: String,
    private val httpClient: HttpClient
) : IRemoteData {
    override suspend fun getCharactersFromApi(): ApiCharactersResponse =
        httpClient.get { apiUrl("/api/character") }

    override suspend fun getCharacterFromApi(id: Int): ApiCharacter =
        httpClient.get { apiUrl("/api/character/$id") }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}