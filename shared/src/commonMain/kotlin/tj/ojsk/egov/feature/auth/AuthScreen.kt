package tj.ojsk.egov.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import database.UserEntity
import egov.shared.generated.resources.Res
import egov.shared.generated.resources.gerb
import egov.shared.generated.resources.logo_filled
import org.jetbrains.compose.resources.painterResource
import tj.ojsk.egov.core.presentation.component.Button
import tj.ojsk.egov.core.presentation.navigation.Destinations
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.feature.auth.view_model.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    onLoginWithUsernamePassword: () -> Unit,
    onLoginWithIMZO: () -> Unit,
) {
    val viewModel: AuthViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Добро пожаловать",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "На единый портал государственных услуг!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
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

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Чтобы использовать все возможности приложения, необходимо войти в систему.",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onLoginWithIMZO,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    text = "Войти по IMZO",
                    isLoading = state.isLoading,
                )

                Spacer(modifier = Modifier.height(12.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.6.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onLoginWithUsernamePassword,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    text = "Войти по электронной почте",
                    outlined = true
                )
            }
        }
    }
}