package tj.ojsk.egov.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    title: String,
    onDismissRequest: () -> Unit,
    sheetContent: @Composable () -> Unit,
    hasChanges: Boolean = false,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            !(it == SheetValue.Hidden && hasChanges)
        }
    )
    val coroutineScope = rememberCoroutineScope()
    var showConfirmDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        sheetState.show()
    }

    ModalBottomSheet(
        onDismissRequest = {
            if (hasChanges) {
                showConfirmDialog = true
            } else {
                coroutineScope.launch {
                    sheetState.hide()
                    onDismissRequest()
                }
            }
        },
        sheetState = sheetState,
        containerColor = Color.White,
        contentWindowInsets = { WindowInsets.ime },
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        SheetTitleWithClose(
            title = title,
            onClose = {
                if (hasChanges) {
                    showConfirmDialog = true
                } else {
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismissRequest()
                    }
                }
            }
        )

        sheetContent()
    }

    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Вы уверены?") },
            text = { Text("У вас есть несохранённые данные. Выйти без сохранения?") },
            confirmButton = {
                TextButton(onClick = {
                    showConfirmDialog = false
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismissRequest()
                    }
                }) {
                    Text("Да")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }
}

@Composable
private fun SheetTitleWithClose(
    title: String,
    onClose: () -> Unit
) {
    ListItem(
        headlineContent = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        trailingContent = {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Закрыть"
                )
            }
        }
    )
    HorizontalDivider(thickness = 1.dp, color = DividerDefaults.color)
}