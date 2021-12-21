package daniel.avila.ricknmortykmm.android.ui.features.favorites


import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import daniel.avila.ricknmortykmm.android.ui.components.ArrowBackIcon

@Composable
fun ActionBar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Characters Favorites") },
        navigationIcon = { ArrowBackIcon(onBackPressed) }
    )
}