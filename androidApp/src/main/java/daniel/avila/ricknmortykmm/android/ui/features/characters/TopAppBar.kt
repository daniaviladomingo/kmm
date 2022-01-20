package daniel.avila.ricknmortykmm.android.ui.features.characters

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import daniel.avila.ricknmortykmm.android.ui.components.ActionBarIcon

@Composable
fun ActionBar(
    actionFavorite: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Rick & Morty KMM") },
        actions = {
            ActionBarIcon(
                onClick = actionFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}