package tj.ojsk.egov.platform

import platform.Foundation.NSBundle

actual val isDebugBuild: Boolean
    get() {
        return NSBundle.mainBundle.infoDictionary?.get("Configuration") == "Debug"
    }