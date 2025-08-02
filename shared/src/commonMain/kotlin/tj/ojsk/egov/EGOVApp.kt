package tj.ojsk.egov

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinContext
import tj.ojsk.egov.core.presentation.theme.EGovTheme
import tj.ojsk.egov.core.utils.koinViewModel
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