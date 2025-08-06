package tj.ojsk.egov.platform

import platform.Foundation.NSLog

actual object Logger {
    actual fun d(tag: String, message: String) {
        NSLog("[$tag] $message")
    }
    actual fun e(tag: String, message: String) {
        NSLog("[$tag] $message")
    }
}
