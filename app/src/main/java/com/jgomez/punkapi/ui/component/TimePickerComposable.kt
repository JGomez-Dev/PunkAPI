package com.jgomez.punkapi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
@ExperimentalMaterial3Api
fun TimePickerComposable(timeState: TimePickerState) {
    TimePicker(state = timeState)
}

@Composable
@ExperimentalMaterial3Api
@Preview
fun TimePickerComposePreview(
) {
    val timeState = rememberTimePickerState(11, 30, false)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TimePickerComposable(timeState = timeState)
        Text(text = "Time is ${timeState.hour} : ${timeState.minute} ")
    }
}