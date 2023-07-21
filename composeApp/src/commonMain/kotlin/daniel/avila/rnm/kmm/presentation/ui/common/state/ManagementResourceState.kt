package daniel.avila.rnm.kmm.presentation.ui.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import daniel.avila.rnm.kmm.presentation.mvi.BasicUiState

@Composable
fun <T> ManagementResourceState(
    resourceState: BasicUiState<T>,
    successView: @Composable (data: T?) -> Unit,
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit,
    msgTryAgain: String = "No data to show",
    onCheckAgain: () -> Unit,
    msgCheckAgain: String = "An error has ocurred"
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        when (resourceState) {
            is BasicUiState.Empty -> Empty(onCheckAgain = onCheckAgain, msg = msgCheckAgain)
            is BasicUiState.Error -> Error(onTryAgain = onTryAgain, msg = msgTryAgain)
            BasicUiState.Loading -> Loading()
            is BasicUiState.Success -> successView(resourceState.data)
            BasicUiState.Idle -> Unit
        }
    }
}