package tj.ojsk.egov.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.jetbrains.compose.resources.painterResource
import tj.ojsk.egov.core.presentation.navigation.Destinations
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.feature.auth.view_model.AuthViewModel

@Composable
fun LoginIMZOScreen(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel()
) {
    var number by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.size(72.dp),
            painter = painterResource(Res.drawable.gerb),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Подключайтесь к нашему сервису быстро и безопасно!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Номер телефона") },
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                if (number.isNotBlank()) {
                    IconButton(onClick = { number = "" }) {
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
            value = verificationCode,
            onValueChange = { verificationCode = it },
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
                if (number.isBlank()) {
                    errorMessage = "Введите номер телефона"
                } else if (verificationCode.isBlank()) {
                    errorMessage = "Введите код из сообщения"
                } else {
                    errorMessage = null
//                    viewModel.loginIMZO(
//                        number = number,
//                        verificationCode = verificationCode,
//                        onSuccess = {
//                            navController.navigate(Destinations.Home)
//                        },
//                        onError = {
//                            errorMessage = it
//                        }
//                    )

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = "Отправить код"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate(Destinations.Login)
        }) {
            Text("Войти по электронной почте")
        }
    }
}