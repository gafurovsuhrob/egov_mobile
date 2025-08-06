package tj.ojsk.egov.platform

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import tj.ojsk.egov.shared.R
import androidx.core.net.toUri

actual class NotificationsManager(
    private val context: Context,
) {
    private val notificationManager get() = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @SuppressLint("MissingPermission")
    actual fun showNotification(
        title: String,
        description: String,
    ) {
        // TODO: use a PendingIntent to open the app on notification click
        // Intent for the notification click
        /*val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            action = UpdateAction
        }*/

        val sound =
            "${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.applicationContext.packageName}/${R.raw.alarm}".toUri()

        println("sound: $sound")

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            // TODO: use a PendingIntent to open the app on notification click
            // .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT + PendingIntent.FLAG_IMMUTABLE))
            .setAutoCancel(true)
            .setSound(sound)

        createNotificationChannel(sound)
        if (areNotificationsEnabled()) {
            NotificationManagerCompat.from(context).notify(notificationId, builder.build())
        }
    }

    private fun areNotificationsEnabled() = NotificationManagerCompat
        .from(context)
        .areNotificationsEnabled()

    private fun createNotificationChannel(sound: Uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,

                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                description = channelDescription
                setSound(sound, Notification.AUDIO_ATTRIBUTES_DEFAULT)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val channelName = "EGOV TJ"
        const val channelDescription = "EGOV TJ notifications"
        const val notificationId = 44
        const val channelId = "EGOV Tj"
    }
}
