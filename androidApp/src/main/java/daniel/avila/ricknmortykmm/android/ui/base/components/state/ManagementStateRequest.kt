package daniel.avila.ricknmortykmm.android.ui.base.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import daniel.avila.ricknmortykmm.shared.base.mvi.StateRequest

@Composable
fun ManagementStateRequest(
    stateRequest: StateRequest,
    successView: @Composable () -> Unit,
    onTryAgain: () -> Unit,
    onCheckAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        when (stateRequest) {
            is StateRequest.Empty -> Empty(onCheckAgain = onCheckAgain)
            is StateRequest.Error -> Error(onTryAgain = onTryAgain)
            StateRequest.Loading -> Loading()
            StateRequest.Success -> successView()
            StateRequest.Idle -> Unit
        }
    }
}