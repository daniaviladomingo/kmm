package daniel.avila.ricknmortykmm.shared.data_remote

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharactersResponse
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.request.*

class RemoteDataImp(
    private val httpClient: HttpClient
): IRemoteData {
    override suspend fun getCharactersFromApi(): ApiCharactersResponse = httpClient.get("https://rickandmortyapi.com/api/character/")

}