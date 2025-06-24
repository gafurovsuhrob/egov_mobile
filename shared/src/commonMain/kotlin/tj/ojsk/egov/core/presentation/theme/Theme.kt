package tj.ojsk.egov.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryColor,
    onSecondary = SecondaryTextColor,
    tertiary = PrimaryLightColor,
    onTertiary = PrimaryTextColor,
    background = BackgroundLightColor,
    onBackground = SecondaryTextColor,
    surface = SurfaceLight,
    onSurface = SecondaryTextColor,
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = Black,
    secondaryContainer = PrimaryColor,
    onSecondaryContainer = White,
    error = ErrorColor,
    onError = OnErrorColor,
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryLightColor,
    onSecondary = SecondaryTextColor,
    tertiary = PrimaryLightColor,
    onTertiary = PrimaryTextColor,
    background = BackgroundDarkColor,
    onBackground = PrimaryTextColor,
    surface = SurfaceDark,
    onSurface = PrimaryTextColor,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = White,
    secondaryContainer = PrimaryColor,
    onSecondaryContainer = White,
    error = ErrorColor,
    onError = OnErrorColor,
)

@Composable
fun EGovTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val autoColors = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = autoColors,
        typography = AppTypography,
        shapes = Shapes,
        content = content,
    )
}
