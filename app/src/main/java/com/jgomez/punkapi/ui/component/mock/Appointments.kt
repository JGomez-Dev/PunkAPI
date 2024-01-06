package com.jgomez.punkapi.ui.component.mock

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private typealias Reminder = Appointments.Reminder
private typealias Repeat = Appointments.Repeat

data class Appointments(
    val time: LocalDateTime,
    val where: String,
    val from: String,
    val reminder: Reminder,
    val repeat: Repeat,
    val note: String
) {
    enum class Reminder(val minutesBefore: Int) {
        NONE(0),
        AT_EVENT_TIME(0),
        FIVE_MINUTES_BEFORE(5),
        TEN_MINUTES_BEFORE(10),
        FIFTEEN_MINUTES_BEFORE(15),
        THIRTY_MINUTES_BEFORE(30),
        ONE_HOUR_BEFORE(60),
        ONE_DAY_BEFORE(1440) // 1440 minutes in a day
    }

    enum class Repeat(val interval: Int) {
        NEVER(0),
        EVERY_DAY(1),
        EVERY_WEEK(7),
        EVERY_TWO_WEEKS(14),
        EVERY_MONTH(30), // This is an approximation for simplicity
        EVERY_YEAR(365) // This is an approximation for simplicity
        // You can add additional functions or properties as needed
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateAppointments(): List<Appointments> = buildList {
    val currentMonth = YearMonth.now()

    currentMonth.atDay(17).also { date ->
        add(
            Appointments(
                time = date.atTime(21, 30),
                where = "Cita digestivo",
                from = "Hospital La Paz",
                reminder = Reminder.AT_EVENT_TIME,
                repeat = Repeat.EVERY_DAY,
                note = "Llamar a Ricardo"
            )
        )
    }

    currentMonth.atDay(22).also { date ->
        add(
            Appointments(
                time = date.atTime(14, 0),
                where = "Reunión de equipo",
                from = "Oficina",
                reminder = Reminder.FIFTEEN_MINUTES_BEFORE,
                repeat = Repeat.EVERY_WEEK,
                note = "Preparar presentación"
            )
        )
    }

    currentMonth.atDay(3).also { date ->
        add(
            Appointments(
                time = date.atTime(10, 0),
                where = "Entrevista",
                from = "Zoom",
                reminder = Reminder.ONE_DAY_BEFORE,
                repeat = Repeat.NEVER,
                note = "Investigar sobre la empresa"
            )
        )
    }

    currentMonth.atDay(12).also { date ->
        add(
            Appointments(
                time = date.atTime(18, 45),
                where = "Clase de yoga",
                from = "Gimnasio local",
                reminder = Reminder.THIRTY_MINUTES_BEFORE,
                repeat = Repeat.EVERY_TWO_WEEKS,
                note = "Llevar esterilla"
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val flightDateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("EEE'\n'dd MMM'\n'HH:mm")