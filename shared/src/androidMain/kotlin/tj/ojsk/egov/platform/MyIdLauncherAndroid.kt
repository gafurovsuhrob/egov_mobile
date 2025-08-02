package tj.ojsk.egov.platform

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

actual class MyIdLauncher(private val context: Context) {

    actual fun launch() {
        val packageName = "tj.dc.myid1a"
        val className = "com.example.facerecognition_flutter.MainActivity" // из adb

        try {
            val intent = Intent(Intent.ACTION_MAIN).apply {
                component = ComponentName(packageName, className)
                addCategory(Intent.CATEGORY_LAUNCHER)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)

        } catch (e: Exception) {
            Log.e("MyIdLauncher", "App launch failed, redirecting to Play Store", e)

            // Переход в Play Store
            val playIntent = Intent(Intent.ACTION_VIEW).apply {
                data = "market://details?id=$packageName".toUri()
                setPackage("com.android.vending")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            try {
                context.startActivity(playIntent)
            } catch (e: ActivityNotFoundException) {
                val webIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = "https://play.google.com/store/apps/details?id=$packageName".toUri()
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(webIntent)

            }
        }
    }
}

@Composable
actual fun rememberMyIdLauncher(): MyIdLauncher {
    val context = LocalContext.current
    return remember(context) { MyIdLauncher(context) }
}