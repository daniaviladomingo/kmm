package daniel.avila.ricknmortykmm.android.ui.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState

@Composable
fun <T> ManagementResourceState(
    resourceState: BasicUiState<T>,
    successView: @Composable (data: T?) -> Unit,
    onTryAgain: () -> Unit,
    onCheckAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        when (resourceState) {
            is BasicUiState.Empty -> Empty(onCheckAgain = onCheckAgain)
            is BasicUiState.Error -> Error(onTryAgain = onTryAgain)
            BasicUiState.Loading -> Loading()
            is BasicUiState.Success -> successView(resourceState.data)
            BasicUiState.Idle -> Unit
        }
    }
}