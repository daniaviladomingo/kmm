package daniel.avila.rnm.kmm

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import daniel.avila.rnm.kmm.theme.AppTheme
import daniel.avila.rnm.kmm.ui.features.characters.CharactersScreen

@Composable
internal fun App() = AppTheme {
    Navigator(CharactersScreen())
}

