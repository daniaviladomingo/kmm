package daniel.avila.rnm.kmm.data_remote.model.mapper

import daniel.avila.rnm.kmm.data_remote.model.ApiCharacter
import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.domain.model.Gender
import daniel.avila.rnm.kmm.domain.model.Status
import daniel.avila.rnm.kmm.domain.model.map.Mapper

class ApiCharacterMapper : Mapper<ApiCharacter, Character>() {
    override fun map(model: ApiCharacter): Character = model.run {
        Character(
            id, name, when (status) {
                "Alive" -> Status.ALIVE
                "Dead" -> Status.DEAD
                else -> Status.UNKNOWN
            }, species, when (gender) {
                "Male" -> Gender.MALE
                "Female" -> Gender.FEMALE
                "Genderless" -> Gender.GENDERLESS
                else -> Gender.UNKNOWN
            }, origin.name, location.name, image
        )
    }

    override fun inverseMap(model: Character): ApiCharacter {
        TODO("Not yet implemented")
    }
}