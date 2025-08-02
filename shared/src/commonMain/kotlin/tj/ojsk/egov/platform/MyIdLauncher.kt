package tj.ojsk.egov.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalGraphicsContext

expect class MyIdLauncher {
    fun launch()
}

@Composable
expect fun rememberMyIdLauncher(): MyIdLauncher