package tj.ojsk.egov.android

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tj.ojsk.egov.EGOVApp
import tj.ojsk.egov.core.presentation.navigation.Destinations
import tj.ojsk.egov.core.utils.navigateToTopLevelDestination
import tj.ojsk.egov.main.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)

        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            var backPressedTime by remember { mutableLongStateOf(0L) }

            var hasNotificationPermission by remember {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.POST_NOTIFICATIONS,
                        ) == PackageManager.PERMISSION_GRANTED,
                    )
                } else {
                    mutableStateOf(true)
                }
            }

            val notificationsPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { granted ->
                    hasNotificationPermission = granted
                },
            )

            LaunchedEffect(key1 = true, block = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    notificationsPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            })


            BackHandler {
                val currentRoute = navController.currentBackStackEntry
                    ?.destination?.route
                    ?.substringBefore("?")

                val homeRoute = Destinations.Home::class.qualifiedName

                if (currentRoute != homeRoute) {
                    navController.navigateToTopLevelDestination(Destinations.Home)
                } else {
                    val now = System.currentTimeMillis()
                    if (now - backPressedTime < 2000) {
                        (context as? Activity)?.finish()
                    } else {
                        backPressedTime = now
                        Toast.makeText(context, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            /*val notificationUtils = NotificationsManager(this)
            notificationUtils.showUpdateNotification(
                "EGov",
                "EGov is running in the background",
            )*/

            EGOVApp()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        intent.data?.let { uri ->
            if (uri.scheme == "egov" && uri.host == "oauth" && uri.path == "/callback") {
                val code = uri.getQueryParameter("code")
                if (!code.isNullOrEmpty()) {
                    val viewModel: MainViewModel by viewModel()
                    viewModel.handleImzoCode(code)
                }
            }
        }
    }
}