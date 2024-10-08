package com.example.abzagencytesttask.navigation
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import destinations.BottomBarDestinations
import destinations.DestinationsId
import presentation.screen.SignUpScreen
import presentation.error_screen.ErrorScreen
import presentation.success_screen.SuccessScreen
import presentation.ui.SplashScreen
import presentation.ui.UsersScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(
    modifier:Modifier,
    navController: NavHostController,
){
    val isShown = derivedStateOf { mutableStateOf(true) }
       NavHost(
           navController = navController,
           startDestination = DestinationsId.SplashScreen.id
       ) {
           composable(DestinationsId.SplashScreen.id) {
               SplashScreen(
                   modifier = modifier,
                   onNavigateToUsersScreen = {
                       navController.navigate(DestinationsId.BottomBar.id)
                   }
               )
           }

           navigation(
               route = DestinationsId.BottomBar.id,
               startDestination = BottomBarDestinations.USERS_SCREEN.routes
           ){
               composable(BottomBarDestinations.USERS_SCREEN.routes){
                   UsersScreen(
                       modifier = modifier,
                   )
               }

               composable(BottomBarDestinations.SIGN_UP_SCREEN.routes){
                  SignUpScreen(
                      modifier = modifier,
                      navigateToErrorStatusScreen = { error->
                          navController.navigate(
                              "SignUpFailed/$error"
                          )
                      },
                      navigateToSuccessStatusScreen = { message->
                          navController.navigate(
                              "SignUpSuccess/$message"
                          )
                      },
                  )
               }
           }

           composable(
               route = DestinationsId.SignUpSuccess.id,
               arguments = listOf(
                   navArgument("successMessage"){
                       type = NavType.StringType
                   }
               )
           ){
               val message = it.arguments?.getString("successMessage")?:""
               SuccessScreen(
                   modifier = modifier,
                   navigateToUsersScreen = {
                       navController.navigate(BottomBarDestinations.USERS_SCREEN.routes)
                   }, message = message
               )
           }

           composable(
               route = DestinationsId.SignUpFailed.id,
               arguments = listOf(
                   navArgument("error"){
                       type = NavType.StringType
                   }
               )
           ){
               val error = it.arguments?.getString("error")?:""
               ErrorScreen(
                   modifier = modifier,
                   navigateBack = {
                       navController.popBackStack()
                   },
                   message = error
               )
           }
       }
}