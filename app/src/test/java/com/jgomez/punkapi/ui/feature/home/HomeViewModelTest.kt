package com.jgomez.punkapi.ui.feature.home

import app.cash.turbine.test
import com.jgomez.punkapi.domain.usecase.beer.GetBeersUseCase
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val getBeersUseCase : GetBeersUseCase = mockk(relaxed = true)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup(){
        viewModel = HomeViewModel (getBeersUseCase)
    }

    @Test
    fun `uiState initially Loading`() = runTest{
        viewModel.state.test {
            val item = awaitItem()
            assertEquals(HomeUiState.Loading, item )
        }
    }

}

