package com.jgomez.punkapi.ui.component

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jgomez.punkapi.R
import com.jgomez.punkapi.ui.component.mock.Appointments
import com.jgomez.punkapi.ui.component.mock.generateAppointments
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
private val appointments = generateAppointments()
private val selectedItemColor: Color @Composable get() = colorResource(R.color.teal_400)
private val inActiveTextColor: Color @Composable get() = colorResource(R.color.white)
private val itemBackgroundColor: Color @Composable get() = colorResource(R.color.white)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarComposable() {

    val context = LocalContext.current

    val currentMonth = remember { YearMonth.now() }
    val currentDate = remember { LocalDate.now() }

    val startMonth = remember { currentMonth }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = firstDayOfWeek
        )
        VerticalCalendar(
            state = state,
            dayContent = { day ->
                Divider(color = Color.Gray)
                Day(
                    isSelected = appointments.any { it.time.toLocalDate() == day.date },
                    appointment = appointments.firstOrNull { it.time.toLocalDate() == day.date },
                    day = day,
                    isToday = day.date == currentDate,
                    isWeekend = day.date.dayOfWeek == DayOfWeek.SUNDAY || day.date.dayOfWeek == DayOfWeek.SATURDAY
                ) {
                    Toast.makeText(context, it.where, Toast.LENGTH_LONG).show()
                }
            },
            monthHeader = { month -> MonthHeader(month) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
    val daysOfWeek = calendarMonth.weekDays.first().map { it.date.dayOfWeek }
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = calendarMonth.yearMonth.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            fontSize = 32.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        //Divider(color = Color.Gray)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun Day(
    day: CalendarDay,
    appointment: Appointments?,
    isToday: Boolean = false,
    isSelected: Boolean = false,
    isWeekend: Boolean,
    onClick: (Appointments) -> Unit,

    ) {
    Box(
        Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .size(37.dp)
            .background(color = if (isSelected) colorResource(R.color.teal_700) else Color.Transparent)
            .clickable(
                onClick = {
                    if (appointment != null) onClick(appointment)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (isSelected) Color.White else if (isWeekend) colorResource(R.color.teal_100) else Color.Black,
            fontSize = 14.sp,
        )
        if (isToday)
            Box(
                Modifier
                    .align(alignment = BottomCenter)
                    .clip(CircleShape)
                    .size(4.dp)
                    .background(color = Color.Blue)
            )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MainScreenPreview() {
    CalendarComposable()
}