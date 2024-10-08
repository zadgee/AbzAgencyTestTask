package com.example.abzagencytesttask.navigation.appState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import destinations.BottomBarDestinations

@Stable
class AppState(
    val navController: NavHostController
) {

    private val routes = BottomBarDestinations.entries.map { it.routes }

    val shouldShowBottomBar: Boolean
        @Composable get() =
            navController.currentBackStackEntryAsState().value?.destination?.route in routes

}