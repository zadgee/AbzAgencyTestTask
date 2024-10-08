package com.example.abzagencytesttask.navigation.bottomBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import compose_ui.colors.blue
import compose_ui.colors.grey
import destinations.BottomBarDestinations

@Composable
fun AppBottomBarRow(
    navController: NavHostController,
    modifier: Modifier
){
    val screens = listOf(
        BottomBarDestinations.USERS_SCREEN,
        BottomBarDestinations.SIGN_UP_SCREEN
    )
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            BottomBarItems(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navController,
                modifier = modifier
            )
        }
    }

}

@Composable
fun BottomBarItems(
    screen: BottomBarDestinations,
    currentDestination: NavDestination?,
    navHostController: NavHostController,
    modifier: Modifier
) {

    val selected = currentDestination?.hierarchy?.any { it.route == screen.routes } == true

    val contentColor = if (selected) blue else grey

    Row(
        modifier = modifier.clickable {
            navHostController.navigate(screen.routes)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = screen.icon),
            contentDescription = "Navigation Icons data",
            tint = contentColor,
            modifier = Modifier.size(30.dp),
        )
        Text(
            modifier = modifier.padding(horizontal = 5.dp),
            text = screen.title,
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
