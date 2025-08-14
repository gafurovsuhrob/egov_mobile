package tj.ojsk.egov

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinContext
import tj.ojsk.egov.core.presentation.theme.EGovTheme
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.feature.auth.view_model.AuthViewModel
import tj.ojsk.egov.main.MainScreen
import tj.ojsk.egov.main.MainViewModel
import tj.ojsk.egov.main.OnBoardingState
import tj.ojsk.egov.platform.StatusBarColors

@Composable
fun EGOVApp(
    mainViewModel: MainViewModel = koinViewModel(),
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    val onBoardingCompleted = mainViewModel.onBoardingCompleted.collectAsState().value

    val mainViewModel: MainViewModel = koinViewModel()
    val authViewModel: AuthViewModel = koinViewModel()

    val imzoCode by mainViewModel.imzoAuthCode.collectAsState()

    LaunchedEffect(imzoCode) {
        if (!imzoCode.isNullOrBlank()) {
            mainViewModel.showLoading()
            val success = authViewModel.loginWithAuthorizationCode(imzoCode!!)
            if (success) mainViewModel.showSuccess("Успешный вход")
            else mainViewModel.showError("Ошибка при входе")
            mainViewModel.handleImzoCode("")
        }
    }

    KoinContext {
        EGovTheme(
            useDarkTheme = darkTheme
        ) {
            val navController = rememberNavController()

            StatusBarColors(
                statusBarColor = MaterialTheme.colorScheme.background,
                navBarColor = MaterialTheme.colorScheme.background,
            )

            when (onBoardingCompleted) {
                is OnBoardingState.Success -> {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        MainScreen(
                            onBoardingCompleted = onBoardingCompleted.completed,
                            navController = navController,
                        )
                    }
                }
                else -> {}
            }
        }
    }
}