package daniel.avila.ricknmortykmm.shared.base.mvi

interface UiState  {
    val stateRequest: StateRequest
}

sealed class StateRequest {
    object Loading : StateRequest()
    data class Error(val message: String? = null) : StateRequest()
    data class Empty(val message: String? = null) : StateRequest()
    object Success : StateRequest()
    object Idle : StateRequest()
}