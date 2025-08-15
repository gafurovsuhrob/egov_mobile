package tj.ojsk.egov.platform


import java.util.Locale

actual fun getCurrentLanguage(): String {
    return Locale.getDefault().language
}