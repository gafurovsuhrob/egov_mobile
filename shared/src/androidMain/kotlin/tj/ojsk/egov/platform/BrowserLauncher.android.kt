package tj.ojsk.egov.platform

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

actual class BrowserLauncher(private val context: Context) {
    actual fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
//этот код тоже не работает
//
//import android.content.Intent
//import android.content.Context
//import androidx.core.net.toUri
//
//actual class BrowserLauncher(private val context: Context) {
//    actual fun openUrl(url: String) {
//        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        }
//        context.startActivity(intent)
//    }
//}