package daniel.avila.ricknmortykmm.shared.base

import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState

interface IBaseView {
    fun <T> managementResourceState(resource: BasicUiState<T>, success: (data: T) -> Unit)
}