package com.jgomez.punkapi.ui.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jgomez.punkapi.domain.model.BeerModel


@Composable
fun DetailScreen(state: DetailUiState) {
    when (state) {
        is DetailUiState.Error -> {
            Text(text = state.message)
        }

        is DetailUiState.Loading -> {
            CircularProgressIndicator()
        }

        is DetailUiState.Success -> {
            with(state.data) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    image?.let {
                        AsyncImage(
                            model = image, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                                .clip(CircleShape)
                                .size(320.dp)
                        )
                    }
                    name?.let {
                        Text(text = name)
                    }
                    description?.let {
                        Text(text = description)
                    }
                    grades?.let {
                        Text(text = grades.toString())
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true, device = "id:pixel_2", showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(DetailUiState.Success(data = BeerModel(id = null, name = null, image = null, description = null, grades = null)))
}