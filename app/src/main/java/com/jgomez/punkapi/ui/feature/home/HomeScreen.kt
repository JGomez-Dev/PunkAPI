package com.jgomez.punkapi.ui.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.ui.theme.MyApplicationTheme

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
            HomeContent(
                items = (items as HomeUiState.Success).data,
                goToDetail = goToDetail,
            )
        }
    }
}

@Composable
private fun HomeContent(
    items: List<BeerModel>,
    goToDetail: (beer: Int?) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue(text = "")) }

    val filteredList = if (textState.text.isEmpty()) {
        items
    } else {
        items.filter { data ->
            data.name!!.startsWith(textState.text, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
    ) {
        TextField(
            value = textState, onValueChange = {
                textState = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            filteredList.forEach { item ->
                ClickableText(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = buildAnnotatedString {
                        append(item.name)
                    },
                    onClick = {
                        goToDetail(item.id)
                    }
                )
            }
        }
    }
}

// Previews
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        HomeContent(listOf(
            BeerModel(id = 1, name = "Aguila", description = "Cerveza muy buena", grades = 7.3, image = ""),
            BeerModel(id = 1, name = "Mahou", description = "Cerveza menos buena", grades = 4.2, image = "")
        ), goToDetail = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        HomeContent(listOf(
            BeerModel(id = 1, name = "Aguila", description = "Cerveza muy buena", grades = 7.4, image = ""),
            BeerModel(id = 1, name = "Mahou", description = "Cerveza menos buena", grades = 4.1, image = "")
        ), goToDetail = {})
    }
}
