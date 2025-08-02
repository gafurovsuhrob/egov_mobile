package tj.ojsk.egov.platform

actual object Logger {
    actual fun d(tag: String, message: String) {
        android.util.Log.d(tag, message)
    }

}
