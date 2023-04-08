package daniel.avila.ricknmortykmm.android.ui.features.characters

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import daniel.avila.ricknmortykmm.android.ui.components.ActionBarIcon

@Composable
fun ActionBar(
    onClickFavorite: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Rick & Morty KMM") },
        actions = {
            ActionBarIcon(
                onClick = onClickFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}