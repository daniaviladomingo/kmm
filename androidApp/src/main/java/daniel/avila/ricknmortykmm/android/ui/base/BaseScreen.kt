package daniel.avila.ricknmortykmm.android.ui.base

import androidx.compose.runtime.Composable
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState

@Composable
fun BaseScreen(
    state: BasicUiState<*> = BasicUiState.Success(Any()),
    topAppBar: @Composable () -> Unit = {},

) {

}