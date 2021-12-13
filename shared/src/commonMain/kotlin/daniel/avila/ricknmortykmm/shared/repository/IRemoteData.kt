package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharacter
import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharactersResponse

interface IRemoteData {
    suspend fun getCharactersFromApi(): ApiCharactersResponse
    suspend fun getCharacterFromApi(id: Int): ApiCharacter
}