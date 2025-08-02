package tj.ojsk.egov.core.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tj.ojsk.egov.core.domain.model.TextFieldState

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: TextFieldState,
    maxLines: Int = 1,
    editable: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column(
        modifier = modifier,
    ) {
        if (label != null) {
            label()
            Spacer(modifier = Modifier.height(4.dp))
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minWidth = 56.dp),
            value = value.text,
            onValueChange = onValueChange,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = textStyle,
            shape = shape,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            keyboardOptions = keyboardOptions,
            readOnly = !editable,
        )
        if (!value.error.isNullOrEmpty()) {
            Text(
                text = value.error,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error,
                ),
            )
        }
    }
}

@Composable
fun EGovDateBoxField(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    currentTextState: TextFieldState,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
) {
    Column {
        if (label != null) {
            label()
            Spacer(modifier = Modifier.height(4.dp))
        }
        Box(
            modifier = modifier
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = if (enabled) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = .4f)
                    },
                    shape = shape,
                )
                .clip(shape)
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.CenterStart,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = currentTextState.text,
                    style = textStyle,
                )
                if (enabled) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp),
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                    )
                }
            }
        }
        if (!currentTextState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = currentTextState.error,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
            )
        }
    }
}
