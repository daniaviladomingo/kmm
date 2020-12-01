package daniel.avila.ricknmortykmm.shared.repository.model.mapper

import daniel.avila.ricknmortykmm.shared.data_remote.model.ApiCharacter
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.map.Mapper

class ApiCharacterMapper: Mapper<ApiCharacter, Character>() {
    override fun map(model: ApiCharacter): Character = model.run {
        Character(id, name, image)
    }

    override fun inverseMap(model: Character): ApiCharacter {
        TODO("Not yet implemented")
    }
}