package tj.ojsk.egov.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import egov.shared.generated.resources.Res
import egov.shared.generated.resources.eye_closed
import egov.shared.generated.resources.eye_open
import egov.shared.generated.resources.gerb
import egov.shared.generated.resources.logo_filled
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import tj.ojsk.egov.core.presentation.component.BaseBottomSheet
import tj.ojsk.egov.core.presentation.navigation.Destinations
import tj.ojsk.egov.feature.auth.view_model.LoginEvent
import tj.ojsk.egov.feature.auth.view_model.LoginUiState

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }

    var backPressedOnce by remember { mutableStateOf(false) }

    LaunchedEffect(backPressedOnce) {
        if (backPressedOnce) {
            delay(2000)
            backPressedOnce = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(72.dp),
                painter = painterResource(Res.drawable.logo_filled),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                modifier = Modifier.size(72.dp),
                painter = painterResource(Res.drawable.gerb),
                contentDescription = null
            )
        }

        Text(
            text = "Добро пожаловать!",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if (email.isNotBlank()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Очистить"
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(Res.drawable.eye_closed)
                else
                    painterResource(Res.drawable.eye_open)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = "Показать пароль")
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Пожалуйста, заполните все поля"
                } else {
                    errorMessage = null
                    navController.navigate(Destinations.Home)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = "Войти"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate(Destinations.LoginIMZO)
        }) {
            Text("Войти через IMZO")
        }
    }
}

@Composable
fun LoginBottomSheetContent(
    state: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    onLoginClicked: () -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = state.username,
            onValueChange = { onEvent(LoginEvent.OnUsernameChanged(it)) },
            label = { Text("Логин") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if (state.username.isNotBlank()) {
                    IconButton(onClick = { onEvent(LoginEvent.OnUsernameChanged("")) }) {
                        Icon(Icons.Default.Clear, contentDescription = null)
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
            label = { Text("Пароль") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(Res.drawable.eye_closed)
                else
                    painterResource(Res.drawable.eye_open)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = "Показать пароль")
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        if (state.errorMessage != null) {
            Text(
                text = state.errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLoginClicked,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Войти")
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}