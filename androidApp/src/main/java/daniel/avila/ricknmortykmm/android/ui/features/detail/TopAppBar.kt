package daniel.avila.ricknmortykmm.android.ui.features.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import daniel.avila.ricknmortykmm.android.R
import daniel.avila.ricknmortykmm.android.ui.base.components.ArrowBackIcon

@Composable
fun ActionBar(
    actionFavorite: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Rick & Morty KMM") },
        navigationIcon = {
            TopAppBar(
                title = { Text(text = "Rick & Morty KMM") },
                navigationIcon = { ArrowBackIcon(onBackPressed) }
            )
        },
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