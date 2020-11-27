package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharacter
import kotlinx.coroutines.flow.Flow

interface IRemoteData {
    fun getCharactersFromApi(): Flow<List<ApiCharacter>>
}