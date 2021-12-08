package daniel.avila.ricknmortykmm.android.ui.features.characters

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import daniel.avila.ricknmortykmm.android.R

@Composable
fun ActionBar(
    actionFavorite: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Rick & Morty KMM") },
        actions = {
            IconButton(onClick = actionFavorite) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_star_rate),
                    contentDescription = null
                )
            }
        }
    )
}