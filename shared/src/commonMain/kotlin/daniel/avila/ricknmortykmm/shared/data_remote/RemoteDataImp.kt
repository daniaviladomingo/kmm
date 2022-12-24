package daniel.avila.ricknmortykmm.shared.data_remote

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharacter
import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharactersResponse
import daniel.avila.ricknmortykmm.shared.data_remote.model.mapper.ApiCharacterMapper
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RemoteDataImp(
    private val endPoint: String,
    private val httpClient: HttpClient,
    private val apiCharacterMapper: ApiCharacterMapper,
) : IRemoteData {
    override suspend fun getCharactersFromApi(): List<Character> =
        apiCharacterMapper.map(
            httpClient.get<ApiCharactersResponse> { apiUrl("/api/character") }.results
        )

    override suspend fun getCharacterFromApi(id: Int): Character =
        apiCharacterMapper.map(
            httpClient.get<ApiCharacter> { apiUrl("/api/character/$id") }
        )

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}