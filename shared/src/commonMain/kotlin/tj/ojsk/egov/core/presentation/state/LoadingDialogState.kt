package tj.ojsk.egov.core.presentation.state

sealed class LoadingDialogState {
    object None : LoadingDialogState()
    object Loading : LoadingDialogState()

    data class Success(val message: String? = null) : LoadingDialogState()
    data class Error(val message: String) : LoadingDialogState()
}