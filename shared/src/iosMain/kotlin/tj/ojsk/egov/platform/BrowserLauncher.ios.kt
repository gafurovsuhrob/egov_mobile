package tj.ojsk.egov.platform

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class BrowserLauncher {
    actual fun openUrl(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}