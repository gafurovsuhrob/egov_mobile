package tj.ojsk.egov.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri

object MyIdLauncher {
    private const val MYID_PACKAGE = "tj.dc.myid1a"
    private const val MYID_PLAY_URL = "https://play.google.com/store/apps/details?id=$MYID_PACKAGE"

    fun launchOrRedirect(context: Context) {
        try {
            // Пример deeplink, заменить при необходимости
            val deeplinkIntent = Intent(Intent.ACTION_VIEW).apply {
                data = "myid://auth".toUri()
                setPackage(MYID_PACKAGE)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(deeplinkIntent)
        } catch (e: ActivityNotFoundException) {
            val playIntent = Intent(Intent.ACTION_VIEW, MYID_PLAY_URL.toUri()).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(playIntent)
            Log.e("MyIdLauncher", "ActivityNotFoundException: ${e.message}")
        }
    }
}