package tj.ojsk.egov.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import egov.shared.generated.resources.Res
import egov.shared.generated.resources.logout_outlined_1
import org.jetbrains.compose.resources.painterResource
import tj.ojsk.egov.core.presentation.component.BaseAlertDialog
import tj.ojsk.egov.core.presentation.component.BaseBottomSheet
import tj.ojsk.egov.core.presentation.component.TopAppBar
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.feature.auth.AuthScreen
import tj.ojsk.egov.feature.auth.LoginBottomSheetContent
import tj.ojsk.egov.feature.auth.view_model.AuthViewModel
import tj.ojsk.egov.feature.auth.view_model.LoginEvent
import tj.ojsk.egov.feature.auth.view_model.LoginMethod

@Composable
fun ProfileScreen(
    navController: NavHostController,
) {
    val profileViewModel: ProfileViewModel = koinViewModel()
    val authViewModel: AuthViewModel = koinViewModel()

    val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()
    val uiState by authViewModel.uiState.collectAsState()

    var showLoginSheet by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            showLoginSheet = false
        }
    }

    Box {
        if (isLoggedIn) {
            ProfileContent(
                navController = navController,
                username = uiState.username,
                onLogoutConfirmed = {
                    authViewModel.logout()
                },
                profileViewModel = profileViewModel
            )
        } else {
            AuthScreen(
                onLoginWithUsernamePassword = {
                    authViewModel.onEvent(LoginEvent.OnLoginMethodSelected(LoginMethod.USERNAME_PASSWORD))
                    showLoginSheet = true
                },
                onLoginWithIMZO = {
                    authViewModel.onEvent(LoginEvent.OnLoginMethodSelected(LoginMethod.IMZO))
                    authViewModel.startImzoLogin()
                }
            )
        }

        if (showLoginSheet) {
            BaseBottomSheet(
                title = "Вход в систему",
                onDismissRequest = { showLoginSheet = false },
                sheetContent = {
                    LoginBottomSheetContent(
                        state = uiState,
                        onEvent = authViewModel::onEvent,
                        onLoginClicked = {
                            authViewModel.onEvent(LoginEvent.OnLoginMethodSelected(LoginMethod.USERNAME_PASSWORD))
                            authViewModel.onEvent(LoginEvent.OnLoginClicked)
                        }
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    navController: NavController,
    username: String,
    onLogoutConfirmed: () -> Unit,
    profileViewModel: ProfileViewModel
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    val user by profileViewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Профиль") },
                actions = {
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(painterResource(Res.drawable.logout_outlined_1), contentDescription = "Выйти")
                    }
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Добро пожаловать, ${user?.firstname} ${user?.lastname}!",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (showLogoutDialog) {
        BaseAlertDialog(
            title = "Выход из профиля",
            text = "Вы действительно хотите выйти?",
            onDismissRequest = { showLogoutDialog = false },
            onConfirm = {
                showLogoutDialog = false
                onLogoutConfirmed()
            }
        )
    }
}