package daniel.avila.ricknmortykmm.shared.data_remote

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharacter
import daniel.avila.ricknmortykmm.shared.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataImp(
    private val httpClient: HttpClient
): IRemoteData {
    override fun getCharactersFromApi(): Flow<List<ApiCharacter>> = flow {
        emit(httpClient.get("https://rickandmortyapi.com/api/character/"))
    }
}