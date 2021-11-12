package daniel.avila.ricknmortykmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import daniel.avila.ricknmortykmm.android.R
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Gender
import daniel.avila.ricknmortykmm.shared.domain.model.Status

@Composable
fun CharacterItem(character: Character) {
    Row() {
        Image(
            painter = painterResource(id = R.drawable.ic_alive),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun PreviewGreeting() {
    CharacterItem(
        character = Character(
            1,
            "Dani",
            Status.ALIVE,
            "Human",
            Gender.MALE,
            "Earth",
            "Pozuelo",
            "Pozuelo"
        )
    )
}