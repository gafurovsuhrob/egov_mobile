package tj.ojsk.egov.core.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    outlined: Boolean = false,
) {
    if (outlined) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = !isLoading,
            shape = MaterialTheme.shapes.medium
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = text, style = MaterialTheme.typography.titleMedium)
            }
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = !isLoading,
            shape = MaterialTheme.shapes.medium
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = text, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}