package tj.ojsk.egov.core.utils

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.currentKoinScope
import tj.ojsk.egov.core.domain.model.SessionType
import kotlin.jvm.JvmInline

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

fun differenceBetweenMinutes(minTime: LocalTime, maxTime: LocalTime): Int {
    return (maxTime.hour - minTime.hour) * 60
}

fun differenceBetweenDays(
    minDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    maxDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
): Int {
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
    return (maxDate.dayOfMonth - minDate.dayOfMonth)
}

fun LocalDate.plusDays(days: Int): LocalDate {
    return this.plus(days, DateTimeUnit.DAY)
}

fun LocalDateTime.plusDays(days: Int): LocalDateTime {
    return this.date.plus(days, DateTimeUnit.DAY).atTime(this.time)
}

fun LocalTime.plusHours(hours: Int): LocalTime {
    val addedHours = this.hour + hours
    return LocalTime(addedHours, this.minute)
}

fun LocalTime.truncatedTo(): LocalTime {
    return LocalTime(this.hour, this.minute)
}

fun min(): LocalTime {
    return LocalTime(0, 0)
}

fun max(): LocalTime {
    return LocalTime(23, 59, 59, 999999999)
}

@JvmInline
value class SplitType private constructor(val value: Int) {
    companion object {
        val None = SplitType(0)
        val Start = SplitType(1)
        val End = SplitType(2)
        val Both = SplitType(3)
    }
}


sealed class ScheduleSize {
    class FixedSize(val size: Dp) : ScheduleSize()
    class FixedCount(val count: Float) : ScheduleSize()

    class Adaptive(val minSize: Dp) : ScheduleSize()
}


fun Long?.selectedDateMillisToLocalDateTime(): LocalDateTime {
    return Instant.fromEpochMilliseconds(this ?: 0)
        .toLocalDateTime(TimeZone.currentSystemDefault())
}

fun calculateFromFocusSessions(
    focusSessions: Int,
    sessionTime: Int = 25,
    shortBreakTime: Int = 5,
    longBreakTime: Int = 15,
    currentLocalDateTime: LocalDateTime,
): LocalTime {
    return if (focusSessions <= 0) {
        currentLocalDateTime.time
    } else {
        val totalSessionTimeMinutes = sessionTime * focusSessions
        val totalShortBreakTimeMinutes = shortBreakTime * (focusSessions - 1)
        val totalLongBreakTimeMinutes = longBreakTime * (focusSessions / 4)
        val totalBreakTimeMinutes = totalShortBreakTimeMinutes + totalLongBreakTimeMinutes
        val totalTaskTimeMinutes = totalSessionTimeMinutes + totalBreakTimeMinutes
        val totalTaskTimeMillis = totalTaskTimeMinutes.toEpochMilliseconds()
        val totalTaskTimeLocalDateTime =
            currentLocalDateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
                .plus(totalTaskTimeMillis).selectedDateMillisToLocalDateTime()
        totalTaskTimeLocalDateTime.time
    }
}

fun Int.toEpochMilliseconds(): Long {
    return this * 60 * 1000L
}

fun LocalDateTime.dateTimeToString(): String {
    return this.toString()
}

fun toLocalDateTime(hour: Int, minute: Int, date: LocalDate): LocalDateTime {
    return LocalDateTime(
        date,
        LocalTime(hour, minute),
    )
}

fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}

fun getThisWeek(): List<LocalDate> {
    /**
     * From Monday to Sunday
     */
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val dayOfWeek = today.dayOfWeek.ordinal
    val startOfWeek = today.minus(dayOfWeek, DateTimeUnit.DAY)

    /**
     * Dates Between startOfWeek and endOfWeek inclusive
     */
    val dates = mutableListOf<LocalDate>()
    for (i in 0..6) {
        dates += startOfWeek.plus(i, DateTimeUnit.DAY)
    }
    return dates
}

fun getPreviousWeek(firstDateOfNextWeek: LocalDate = today().date): List<LocalDate> {
    /**
     * From Monday to Sunday
     */
    val startOfWeek = firstDateOfNextWeek.minus(7, DateTimeUnit.DAY)

    /**
     * Dates Between startOfWeek and endOfWeek inclusive
     */
    val dates = mutableListOf<LocalDate>()
    for (i in 0..6) {
        dates += startOfWeek.plus(i, DateTimeUnit.DAY)
    }
    return dates
}

/**
 * A function that will return the last 12 weeks
 * The list should be in descending order
 * The first item should be the current week - This week
 * The second item should be the previous week - Last week
 *
 * The format of the result should be like <String, List<LocalDate>>
 * For this week  -> <This Week, List<LocalDate>>
 * For the other weeks -> <Aug 1 - Aug 7, List<LocalDate>>
 * If a week is outside of this year then it should be <Dec 25, 2020 - Dec 31, 2020, List<LocalDate>>
 */
fun getLast52Weeks(): List<Pair<String, List<LocalDate>>> {
    val thisYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    val thisWeek = getThisWeek()
    val previousWeek = getPreviousWeek(thisWeek.first())
    val weeks = mutableListOf<Pair<String, List<LocalDate>>>()
    weeks += "This Week" to thisWeek
    weeks += "Last Week" to previousWeek
    for (i in 0..51) {
        val week = getPreviousWeek(firstDateOfNextWeek = weeks.last().second.first())
        weeks += "${
            week.first().month.name.lowercase().capitalize(Locale.current).substring(
                0,
                3,
            )
        } ${week.first().dayOfMonth} ${if (week.first().year != thisYear) week.first().year else ""}" +
                " - ${
                    week.last().month.name.lowercase().capitalize(Locale.current).substring(
                        0,
                        3,
                    )
                } ${week.last().dayOfMonth} ${if (week.last().year != thisYear) week.last().year else ""}" to week
    }
    return weeks
}

fun List<Float>.aAllEntriesAreZero(): Boolean {
    return all { it.toDouble() == 0.0 }
}

fun LocalDate.prettyFormat(): String {
    return "${this.dayOfMonth}${
        when (this.dayOfMonth) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
    }, ${this.month.name.lowercase().capitalize(Locale.current).substring(0, 3)} ${this.year}"
}

fun LocalDate.prettyPrintedMonthAndYear(): String {
    return "${this.month.name.lowercase().capitalize(Locale.current).substring(0, 3)} ${this.year}"
}

fun prettyTimeDifference(start: LocalDateTime, end: LocalDateTime, timeFormat: Int): String {
    return if (timeFormat == 12) {
        val startHourTo12HourSystem = if (start.hour > 12) {
            start.hour - 12
        } else {
            start.hour
        }
        val endHourTo12HourSystem = if (end.hour > 12) {
            end.hour - 12
        } else {
            end.hour
        }
        "$startHourTo12HourSystem:${start.minute.formattedZeroMinutes()} ${if (start.hour > 12) "PM" else "AM"} - ${
            endHourTo12HourSystem
        }:${end.minute.formattedZeroMinutes()} ${if (end.hour > 12) "PM" else "AM"}"
    } else {
        "${start.hour}:${start.minute.formattedZeroMinutes()} - ${end.hour}:${end.minute.formattedZeroMinutes()}"
    }
}

fun Int.formattedZeroMinutes(): String {
    return if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}

fun Long.formattedZeroMinutes(): String {
    return if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}

fun LocalTime.formattedTimeBasedOnTimeFormat(timeFormat: Int): String {
    return if (timeFormat == 12) {
        val hourTo12HourSystem = if (this.hour > 12) {
            this.hour - 12
        } else {
            this.hour
        }
        "$hourTo12HourSystem:${
            this.minute.formattedZeroMinutes()
        } ${if (this.hour > 12) "PM" else "AM"}"
    } else {
        "${this.hour}:${this.minute.formattedZeroMinutes()}"
    }
}

fun String.timeFormat(): Int {
    return if (this == "12-hour") {
        12
    } else {
        24
    }
}

fun Int.timeFormat(): String {
    return if (this == 12) {
        "12-hour"
    } else {
        "24-hour"
    }
}


/**
 * LocalDates for List<LocalDate>
 * The last 1 year
 * The next 1 year
 */
fun calendarLocalDates(): List<LocalDate> {
    val thisYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    val lastYear = thisYear - 1
    val nextYear = thisYear + 1
    val dates = mutableListOf<LocalDate>()
    for (i in 0..365) {
        dates += LocalDate(thisYear, 1, 1).plus(i, DateTimeUnit.DAY)
    }
    for (i in 0..365) {
        dates += LocalDate(lastYear, 1, 1).plus(i, DateTimeUnit.DAY)
    }
    for (i in 0..365) {
        dates += LocalDate(nextYear, 1, 1).plus(i, DateTimeUnit.DAY)
    }
    return dates
}

fun LocalDate.insideThisWeek(): Boolean {
    val thisWeek = getThisWeek()
    return this in thisWeek
}

fun today(): LocalDateTime {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
}

fun Long.toTimer(): String {
    /**
     * Input is in milliseconds
     */
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    return "${
        if (hours > 0) {
            hours.formattedZeroMinutes() + ":"
        } else {
            ""
        }
    }${(minutes - (hours * 60)).formattedZeroMinutes()}:${(seconds - (minutes * 60)).formattedZeroMinutes()}"
}

fun Long.toPercentage(total: Long): Float {
    /**
     * In increase order
     */
    return if (total == 0L) {
        0F
    } else {
        val perc = (100 - ((this.toFloat() / total.toFloat()) * 100))
        perc
    }
}

fun Long.toMinutes(): Int {
    return (this / 1000 / 60).toInt()
}

fun Int.toMillis(): Long {
    /**
     * Input is minutes
     */
    return (this * 60 * 1000).toLong()
}

fun String?.sessionType(): SessionType {
    return when (this) {
        "Focus" -> SessionType.Focus
        "ShortBreak" -> SessionType.ShortBreak
        "LongBreak" -> SessionType.LongBreak
        else -> SessionType.Focus
    }
}

fun String.pickFirstName(): String {
    return this.split(" ").first()
}

fun LocalDateTime.calculateEndTime(
    focusSessions: Int,
    sessionTime: Int,
    shortBreakTime: Int,
    longBreakTime: Int,
): LocalDateTime {
    val totalSessionTimeMinutes = sessionTime * focusSessions
    val totalShortBreakTimeMinutes = shortBreakTime * (focusSessions - 1)
    val totalLongBreakTimeMinutes = longBreakTime * (focusSessions / 4)
    val totalBreakTimeMinutes = totalShortBreakTimeMinutes + totalLongBreakTimeMinutes
    val totalTaskTimeMinutes = totalSessionTimeMinutes + totalBreakTimeMinutes
    val totalTaskTimeMillis = totalTaskTimeMinutes.toEpochMilliseconds()
    return toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        .plus(totalTaskTimeMillis).selectedDateMillisToLocalDateTime()
}

fun Int.formattedNumber(): String {
    return "$this${
        when (this) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
    }"
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}