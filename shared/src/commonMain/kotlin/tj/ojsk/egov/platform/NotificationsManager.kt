package tj.ojsk.egov.platform

expect class NotificationsManager {
    fun showNotification(
        title: String,
        description: String,
    )
}
