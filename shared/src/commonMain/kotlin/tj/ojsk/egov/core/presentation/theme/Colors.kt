package tj.ojsk.egov.core.presentation.theme

import androidx.compose.ui.graphics.Color

// Основной зелёный стиль
val PrimaryColor = Color(0xFF74B485)
val PrimaryLightColor = PrimaryColor.copy(alpha = 0.75f)
val PrimaryDarkColor = Color(0xFF4F8E62)

// Второстепенные цвета (мягкие зелёные оттенки)
val SecondaryColor = Color(0xFFB8D8C7)
val SecondaryLightColor = SecondaryColor.copy(alpha = 0.75f)

// Текстовые цвета
val PrimaryTextColor = Color(0xFFFFFFFF)
val SecondaryTextColor = Color(0xFF000000)

// Цвета фона
val SurfaceLight = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF161616)

val BackgroundLightColor = Color(0xFFF1F5F0)  // светлый фон с зеленоватым оттенком
val BackgroundDarkColor = Color(0xFF0A0F0C)   // тёмный фон с зеленоватыми нотками

// Цвета состояния
val ErrorColor = Color(0xFFFF6F61)    // красный оттенок для ошибок
val OnErrorColor = Color(0xFF000000)
val SuccessColor = Color(0xFF34B233)  // зелёный цвет успеха

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

// Цвета Pomodoro таймера
val SessionColor = 0xFF74B485.toInt()      // основной зелёный
val ShortBreakColor = 0xFFB2E0B2.toInt()   // светло-зелёный
val LongBreakColor = 0xFF5E9C76.toInt()    // тёмно-зелёный

// Дополнительные цвета
val Red = Color(0xFFFF0000)
val Orange = Color(0xFFFFA500)
val Blue = Color(0xFF0000FF)
val Green = Color(0xFF00FF00)

val LightGreen = Color(0xFF90EE90)
val Yellow = Color(0xFFFFFF00)
val LightBlue = Color(0xFFADD8E6)
val Pink = Color(0xFFFFC0CB)
