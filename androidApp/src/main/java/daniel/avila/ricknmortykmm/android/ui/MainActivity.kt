package daniel.avila.ricknmortykmm.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.model.CharacterParcelable
import daniel.avila.ricknmortykmm.android.ui.features.characters.CharactersScreen
import daniel.avila.ricknmortykmm.android.ui.features.detail.CharacterDetailScreen
import daniel.avila.ricknmortykmm.android.ui.features.favorites.CharactersFavoriteScreen
import daniel.avila.ricknmortykmm.android.ui.navigation.Navigation
import daniel.avila.ricknmortykmm.android.ui.theme.RickNMortyKmmTheme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickNMortyKmmTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }
}


