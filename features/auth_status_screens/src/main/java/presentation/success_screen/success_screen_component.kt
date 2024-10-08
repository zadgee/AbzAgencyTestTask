package presentation.success_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import compose_ui.colors.black

@Composable
fun SuccessScreenComponent(
    modifier: Modifier,
    message:String,
    navigateToUsersScreen:()->Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                modifier = modifier.size(150.dp),
                painter = painterResource(
                    id = com.example.uikit.R.drawable.success_image
                ),
                contentDescription = "App Logo",
            )
            Text(
                text = message,
                color = black,
                style = MaterialTheme.typography.headlineMedium,
            )

            Image(
                painter = painterResource(
                    id = com.example.uikit.R.drawable.close_button
                ) , contentDescription = "Close button",
                modifier = modifier.clickable {
                    navigateToUsersScreen()
                }.align(Alignment.End)
            )

        }
    }