package com.jgomez.punkapi.ui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgomez.punkapi.core.utils.map
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.domain.usecase.beer.GetBeerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBeerByIdUseCase: GetBeerByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val state: StateFlow<DetailUiState> = _state.asStateFlow()

    fun getBeerById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getBeerByIdUseCase.invoke(id)
                .collect { response ->
                    response.map(
                        onSuccess = { beerModel ->
                            _state.value = DetailUiState.Success(data = beerModel)
                        },
                        onFailure = {
                            _state.value = DetailUiState.Error(message = it.message)
                        }
                    )
                }
        }
    }
}

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Error(val message: String) : DetailUiState
    data class Success(val data: BeerModel) : DetailUiState
}
