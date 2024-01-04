package com.jgomez.punkapi.ui.feature.detail

import app.cash.turbine.test
import com.jgomez.punkapi.core.utils.eitherFailure
import com.jgomez.punkapi.core.utils.eitherSuccess
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.domain.model.GenericError
import com.jgomez.punkapi.domain.usecase.beer.GetBeerByIdUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private val getBeerByIdUseCase: GetBeerByIdUseCase = mockk(relaxed = true)
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(getBeerByIdUseCase)
    }

    @Test
    fun `uiState initially Loading`() = runTest{
        viewModel.state.test {
            val item = awaitItem()
            assertEquals(DetailUiState.Loading, item)
        }
    }

    @Test
    fun `getBeerById should update state with Success when invoked`() = runTest {
        val fakeBeer = mockk<BeerModel>()
        every { getBeerByIdUseCase.invoke(1) } returns flowOf(eitherSuccess((fakeBeer)))

        viewModel.state.test {
            viewModel.getBeerById(1)
            awaitItem()
            val item = awaitItem()
            assertEquals(DetailUiState.Success(fakeBeer), item)
        }
    }

    @Test
    fun `getBeerById should update state with Error when invoked`() = runTest {
        every { getBeerByIdUseCase.invoke(1) } returns flowOf(eitherFailure(GenericError(message = "errorMockk")))

        viewModel.state.test {
            viewModel.getBeerById(1)
            awaitItem()
            val item = awaitItem()
            assertEquals(DetailUiState.Error(message = "errorMockk"), item)
        }
    }
}
