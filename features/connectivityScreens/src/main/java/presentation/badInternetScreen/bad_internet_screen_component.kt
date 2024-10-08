package presentation.badInternetScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import compose_ui.colors.black
import compose_ui.colors.white
import compose_ui.colors.yellow

@Composable
fun BadInternetScreenComponent(
    modifier: Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = yellow
            )
    ) {
        Image(
            modifier = modifier.size(100.dp),
            painter = painterResource(
                id = com.example.uikit.R.drawable.no_connection_image
            ),
            contentDescription = "App Logo",
        )
        Text(
            text = "Bad internet connection",
            color = black,
            style = MaterialTheme.typography.headlineMedium,
        )

        CircularProgressIndicator(
            color = white
        )
    }
}