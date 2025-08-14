package tj.ojsk.egov.core.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import tj.ojsk.egov.core.presentation.state.LoadingDialogState
import kotlin.time.TimeSource

@Composable
fun LoadingDialog(
    state: LoadingDialogState,
    onDismiss: () -> Unit = {},
    fullScreen: Boolean = true
) {
    if (state is LoadingDialogState.None) return

    var showLoading by remember { mutableStateOf(state is LoadingDialogState.Loading) }
    var loadingStartMark = remember { TimeSource.Monotonic.markNow() }

    LaunchedEffect(state) {
        if (state is LoadingDialogState.Loading) {
            loadingStartMark = TimeSource.Monotonic.markNow()
            showLoading = true
        } else {
            val elapsed = loadingStartMark.elapsedNow()
            val remaining = 1000 - elapsed.inWholeMilliseconds
            if (remaining > 0) delay(remaining)
            showLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        if (fullScreen) {
            if (showLoading) {
                FullScreenLoading()
            } else {
                AnimatedResultDialog(state, onDismiss)
            }
        } else {
            AnimatedVisibility(
                visible = showLoading || state !is LoadingDialogState.Loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 8.dp,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .widthIn(min = 280.dp, max = 320.dp)
                        .padding(16.dp)
                ) {
                    if (showLoading) {
                        DialogLoadingContent()
                    } else {
                        DialogContent(state, onDismiss)
                    }
                }
            }
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Пожалуйста, подождите...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun DialogLoadingContent() {
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Пожалуйста, подождите...")
    }
}

@Composable
private fun AnimatedResultDialog(state: LoadingDialogState, onDismiss: () -> Unit) {
    AnimatedVisibility(visible = true, enter = fadeIn()) {
        when (state) {
            is LoadingDialogState.Success -> {
                LaunchedEffect(Unit) {
                    delay(750)
                    onDismiss()
                }
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {},
                    containerColor = Color.White,
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Успешно")
                        }
                    },
                    text = {
                        Text(state.message ?: "Операция выполнена успешно")
                    }
                )
            }
            is LoadingDialogState.Error -> {
                AlertDialog(
                    onDismissRequest = onDismiss,
                    confirmButton = {
                        TextButton(onClick = onDismiss) {
                            Text("Закрыть")
                        }
                    },
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Ошибка")
                        }
                    },
                    text = {
                        Text(state.message, color = MaterialTheme.colorScheme.error)
                    }
                )
            }
            else -> {}
        }
    }
}

@Composable
private fun DialogContent(state: LoadingDialogState, onDismiss: () -> Unit) {
    when (state) {
        is LoadingDialogState.Success -> {
            LaunchedEffect(Unit) {
                delay(750)
                onDismiss()
            }
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(state.message ?: "Операция выполнена успешно")
            }
        }
        is LoadingDialogState.Error -> {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(state.message, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss) {
                    Text("Закрыть")
                }
            }
        }
        else -> {}
    }
}