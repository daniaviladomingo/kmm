package daniel.avila.ricknmortykmm.android.ui.features.detail

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import daniel.avila.ricknmortykmm.android.R
import daniel.avila.ricknmortykmm.android.ui.base.components.ActionBarIcon
import daniel.avila.ricknmortykmm.android.ui.base.components.ArrowBackIcon

@Composable
fun ActionBar(
    name: String,
    isFavorite: Boolean,
    actionAddFavorite: () -> Unit,
    actionRemoveFavorite: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            if (isFavorite) {
                ActionBarIcon(
                    onClick = actionRemoveFavorite,
                    iconResource = R.drawable.ic_action_star_rate
                )
            } else {
                ActionBarIcon(
                    onClick = actionAddFavorite,
                    iconResource = R.drawable.ic_action_star_border
                )
            }
        }
    )
}