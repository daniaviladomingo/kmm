package daniel.avila.ricknmortykmm.androidApp.model.mapper

import daniel.avila.ricknmortykmm.androidApp.model.CharacterParcelable
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.map.Mapper

class CharacterMapper: Mapper<Character, CharacterParcelable>() {
    override fun map(model: Character): CharacterParcelable = with(model){
        CharacterParcelable(id, name, status, species, gender, origin, location, image)
    }

    override fun inverseMap(model: CharacterParcelable): Character = with(model){
        Character(id, name, status, species, gender, origin, location, image)
    }
}