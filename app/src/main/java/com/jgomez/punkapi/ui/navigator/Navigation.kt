package com.jgomez.punkapi.ui.navigator

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jgomez.punkapi.ui.feature.detail.DetailScreen
import com.jgomez.punkapi.ui.feature.detail.DetailViewModel
import com.jgomez.punkapi.ui.feature.home.HomeScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.HomeScreen.route) {

        composable(Destination.HomeScreen.route) {
            HomeScreen(goToDetail = { idBeer ->
                navController.currentBackStackEntry?.savedStateHandle?.set("id", idBeer)
                navController.navigate(Destination.DetailScreen.route)
            })
        }

        composable(Destination.DetailScreen.route) {
            val idBeer: Int? = navController.previousBackStackEntry?.savedStateHandle?.get("id")
            val viewModel: DetailViewModel = hiltViewModel()
            idBeer?.let {
                viewModel.getBeerById(idBeer)
            }
            val state = viewModel.state.collectAsStateWithLifecycle()
            Box(modifier = Modifier.testTag("BeerDetail")){
                DetailScreen(state = state.value)
            }

        }
    }
}
