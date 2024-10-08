package com.example.abzagencytesttask.navigation.appState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun rememberAppState(
    navController: NavHostController
)= remember(navController) {
    AppState(navController)
}
