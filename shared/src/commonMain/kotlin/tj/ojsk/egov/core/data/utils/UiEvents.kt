package tj.ojsk.egov.core.data.utils

sealed class UiEvents {
    data class ShowSnackbar(val message: String) : UiEvents()
    data object Navigation : UiEvents()
    data object NavigateBack : UiEvents()
}
