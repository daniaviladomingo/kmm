package daniel.avila.ricknmortykmm.shared.repository

import daniel.avila.ricknmortykmm.shared.domain.model.Character

interface IRemoteData {
    suspend fun getCharactersFromApi(): List<Character>
    suspend fun getCharacterFromApi(id: Int): Character
}