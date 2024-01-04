package com.jgomez.punkapi.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgomez.punkapi.core.utils.map
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.domain.usecase.beer.GetBeersUseCase
import com.jgomez.punkapi.ui.feature.home.HomeUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(Loading)
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        getAllBeer()
    }

    private fun getAllBeer() {
        viewModelScope.launch(Dispatchers.IO) {
            getBeersUseCase.invoke()
                .collect { response ->
                    response.map(
                        onSuccess = { beerModelList ->
                            _state.value = HomeUiState.Success(data = beerModelList)
                        },
                        onFailure = {
                            _state.value = HomeUiState.Error(message = it.message)
                        }
                    )
                }
        }
    }
}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Success(val data: List<BeerModel>) : HomeUiState
}
