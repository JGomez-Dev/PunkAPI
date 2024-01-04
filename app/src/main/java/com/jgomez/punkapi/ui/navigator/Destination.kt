package com.jgomez.punkapi.ui.navigator

internal sealed class Destination(val route: String) {
    data object HomeScreen: Destination(route = "homeScreen")
    data object DetailScreen: Destination(route = "detailScreen")
}
