package com.jgomez.punkapi.ui.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jgomez.punkapi.ui.component.CalendarComposable
import com.jgomez.punkapi.ui.component.TimePickerComposable
import com.jgomez.punkapi.ui.theme.MyApplicationTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    goToDetail: (beer: Int?) -> Unit
) {
    val items by viewModel.state.collectAsStateWithLifecycle()

    when (items) {
        is HomeUiState.Error -> {
            Text(text = (items as HomeUiState.Error).message)
        }

        is HomeUiState.Loading -> {
            CircularProgressIndicator()
        }

        is HomeUiState.Success -> {
            HomeContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val timeState = rememberTimePickerState(11, 30, false)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TimePickerComposable(timeState = timeState)
            Text(text = "Time is ${timeState.hour} : ${timeState.minute} ")
        }
        CalendarComposable()
    }
}

// Previews
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        HomeContent()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        HomeContent()
    }
}
