package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharactersResponse

interface IRemoteData {
    suspend fun getCharactersFromApi(): ApiCharactersResponse
}