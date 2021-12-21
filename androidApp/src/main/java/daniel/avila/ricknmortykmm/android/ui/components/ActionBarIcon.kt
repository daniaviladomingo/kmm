package daniel.avila.ricknmortykmm.android.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun ActionBarIcon(
    onClick: () -> Unit,
    iconResource: Int
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null
        )
    }
}