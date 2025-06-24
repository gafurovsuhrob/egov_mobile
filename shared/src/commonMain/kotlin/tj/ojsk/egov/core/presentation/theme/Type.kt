package tj.ojsk.egov.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import egov.shared.generated.resources.Res
import egov.shared.generated.resources.rubik_black
import egov.shared.generated.resources.rubik_bold
import egov.shared.generated.resources.rubik_extrabold
import egov.shared.generated.resources.rubik_italic
import egov.shared.generated.resources.rubik_light
import egov.shared.generated.resources.rubik_medium
import egov.shared.generated.resources.rubik_regular
import egov.shared.generated.resources.rubik_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun rubikFontFamily(): FontFamily {
    val regular =
        Font(
            resource = Res.font.rubik_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal,
        )

    val bold =
        Font(
            resource = Res.font.rubik_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        )

    val italic =
        Font(
            resource = Res.font.rubik_italic,
            weight = FontWeight.Normal,
            style = FontStyle.Italic,
        )

    val extraBold =
        Font(
            resource = Res.font.rubik_extrabold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        )

    val semiBold =
        Font(
            resource = Res.font.rubik_semibold,
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal,
        )

    val light =
        Font(
            resource = Res.font.rubik_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal,
        )

    val medium =
        Font(
            resource = Res.font.rubik_medium,
            weight = FontWeight.Medium,
            style = FontStyle.Normal,
        )

    val black =
        Font(
            resource = Res.font.rubik_black,
            weight = FontWeight.Black,
            style = FontStyle.Normal,
        )

    return FontFamily(
        regular,
        bold,
        extraBold,
        semiBold,
        light,
        italic,
        medium,
        black,
    )
}

val AppTypography: Typography
    @Composable
    get() = getTypography()

@Composable
internal fun getTypography(): Typography {
    val rubik = rubikFontFamily()
    return Typography(
        displayLarge = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 50.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 40.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 30.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 28.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W700,
            fontSize = 14.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 11.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 13.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W400,
            fontSize = 11.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.W500,
            fontSize = 9.sp,
        ),
    )
}