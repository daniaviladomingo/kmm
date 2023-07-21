package daniel.avila.rnm.kmm

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import daniel.avila.rnm.kmm.presentation.ui.theme.AppTheme
import daniel.avila.rnm.kmm.presentation.ui.features.characters.CharactersScreen

@Composable
internal fun App() = AppTheme {
    Navigator(CharactersScreen())
}

