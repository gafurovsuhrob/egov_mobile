package tj.ojsk.egov.platform

import androidx.compose.runtime.Composable

actual class MyIdLauncher {
    actual fun launch() {
        // Пока не реализовано — можно оставить заглушку
    }
}

@Composable
actual fun rememberMyIdLauncher(): MyIdLauncher = MyIdLauncher()