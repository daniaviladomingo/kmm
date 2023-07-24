package daniel.avila.rnm.kmm.presentation.ui.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import daniel.avila.rnm.kmm.presentation.mvi.BasicUiState

@Composable
fun <T> ManagementResourceState(
    modifier: Modifier = Modifier,
    resourceState: BasicUiState<T>,
    successView: @Composable (data: T) -> Unit,
    loadingView: @Composable () -> Unit = { Loading() },
    onTryAgain: () -> Unit,
    msgTryAgain: String = "No data to show",
    onCheckAgain: () -> Unit,
    msgCheckAgain: String = "An error has ocurred"
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        when (resourceState) {
            is BasicUiState.Empty -> Empty(modifier = modifier, onCheckAgain = onCheckAgain, msg = msgCheckAgain)
            is BasicUiState.Error -> Error(modifier = modifier, onTryAgain = onTryAgain, msg = msgTryAgain)
            BasicUiState.Loading -> loadingView()
            is BasicUiState.Success -> successView(resourceState.data)
            BasicUiState.Idle -> Unit
        }
    }
}