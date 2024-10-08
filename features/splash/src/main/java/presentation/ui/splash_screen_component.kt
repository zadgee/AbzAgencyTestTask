package presentation.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import compose_ui.colors.black
import compose_ui.colors.yellow

@Composable
fun SplashScreenComponent(
    modifier:Modifier
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
                    id = com.example.uikit.R.drawable.app_logo
                ),
                contentDescription = "App Logo",
            )
            Text(
                text = "TESTTASK",
                color = black,
                fontFamily = FontFamily(
                    Font(
                        com.example.uikit.R.font.nunito_sans_semibold
                    ))
            )
        }
}