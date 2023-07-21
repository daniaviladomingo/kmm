package daniel.avila.rnm.kmm.presentation.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ActionBarIcon(
    onClick: () -> Unit,
    icon: ImageVector
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}