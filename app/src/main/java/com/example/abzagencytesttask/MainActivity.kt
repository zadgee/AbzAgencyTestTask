package com.example.abzagencytesttask
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.abzagencytesttask.navigation.AppNavigation
import com.example.abzagencytesttask.navigation.appState.rememberAppState
import com.example.abzagencytesttask.navigation.bottomBar.AppBottomBarRow
import compose_ui.colors.bottomBarColor
import compose_ui.theme.AbzAgencyTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import domain.handler.InternetConnectivityHandler
import domain.state.ConnectivityState
import presentation.badInternetScreen.BadInternetScreen
import presentation.noInternetScreen.NoInternetScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityState
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbzAgencyTestTaskTheme {
                val navController = rememberNavController()
                val appState = rememberAppState(navController)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if(appState.shouldShowBottomBar){
                            BottomAppBar(
                                containerColor = bottomBarColor,
                                contentPadding = PaddingValues(horizontal = 20.dp),
                                modifier = Modifier
                                    .height(75.dp)
                            ) {
                                AppBottomBarRow(
                                    navController = navController,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                ) {
                    val context = LocalContext.current
                    connectivityObserver = InternetConnectivityHandler(context)
                    val status by connectivityObserver.observeConnection().collectAsState(
                        initial = context.applicationContext
                    )
                    when(status){
                      ConnectivityState.Status.Lost -> NoInternetScreen(
                          modifier = Modifier
                      )
                        ConnectivityState.Status.Available -> AppNavigation(
                        modifier = Modifier,
                        navController = navController,
                      )
                      ConnectivityState.Status.Losing -> BadInternetScreen(
                          modifier = Modifier
                      )
                      ConnectivityState.Status.Unavailable -> NoInternetScreen(
                          modifier = Modifier
                      )
                    }
                }
            }
        }
    }
}