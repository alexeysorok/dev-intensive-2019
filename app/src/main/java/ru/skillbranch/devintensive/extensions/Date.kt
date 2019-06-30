package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units:TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY


    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {

//    0с - 1с "только что"
//    1с - 45с "несколько секунд назад"
//    45с - 75с "минуту назад"
//    75с - 45мин "N минут назад"
//    45мин - 75мин "час назад"
//    75мин 22ч "N часов назад"
//    22ч - 26ч "день назад"
//    26ч - 360д "N дней назад"
//    >360д "более года назад"

    val myTime: Long = this.time
    val nowTime: Long = Date().time
    fun getDec(number: Int, nominativ: String, genetiv: String, plural: String): String {

        var number = number % 100
        if (number >= 11 && number <= 19) return plural

        var i = number % 10
        when (i) {
            1 -> return nominativ
            in (2..4) -> return genetiv
            else -> return plural

        }
    }

    if (myTime < nowTime) {

        var diffTime: Long = nowTime - myTime


        val intMin = (diffTime / MINUTE).toInt()
        val wMinut = getDec(
            intMin,
            "минуту", "минуты", "минут"
        )

        val intHours = (diffTime / HOUR).toInt()
        val wHours = getDec(intHours, "час", "часа", "часов")

        val intDays = (diffTime / DAY).toInt()
        val wDays = getDec(intDays, "день", "дня", "дней")

        when (diffTime / SECOND) {
            in (0..1) -> return "только что"
            in (1..45) -> return "несколько секунд назад"
            in (45..75) -> return "минуту назад"
            in (75..45 * 60) -> return "$intMin $wMinut назад"
            in (45 * 60..22 * 60 * 60) -> return "$intHours $wHours назад"
            in (22 * 60 * 60..26 * 60 * 60) -> return "день назад"
            in (26 * 60 * 60..60 * 60 * 24 * 360) -> return "$intDays $wDays назад"

            else -> return "более года назад"
        }
    }
    else {
        var diffTime: Long = myTime - nowTime


        val intMin = (diffTime / MINUTE).toInt()
        val wMinut = getDec(
            intMin,
            "минуту", "минуты", "минут"
        )

        val intHours = (diffTime / HOUR).toInt()
        val wHours = getDec(intHours, "час", "часа", "часов")

        val intDays = (diffTime / DAY).toInt()
        val wDays = getDec(intDays, "день", "дня", "дней")

        when (diffTime / SECOND) {
            in (0..1) -> return "только что"
            in (1..45) -> return "через несколько секунд"
            in (45..75) -> return "через минуту"
            in (75..45 * 60) -> return "через $intMin $wMinut"
            in (45 * 60..22 * 60 * 60) -> return "через $intHours $wHours"
            in (22 * 60 * 60..26 * 60 * 60) -> return "через один день"
            in (26 * 60 * 60..60 * 60 * 24 * 360) -> return "через $intDays $wDays"

            else -> return "более чем через год"
        }
    }
}







enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}