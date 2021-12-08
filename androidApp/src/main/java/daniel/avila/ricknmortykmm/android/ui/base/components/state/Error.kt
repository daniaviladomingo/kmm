package daniel.avila.ricknmortykmm.android.ui.base.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Error(
    msg: String = "An error has ocurred",
    onTryAgain: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = msg,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(
                onClick = onTryAgain
            ) {
                Text(
                    text = "Try Again",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}