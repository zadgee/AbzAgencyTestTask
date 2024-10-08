package presentation.ui
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.async
import presentation.ui.viewModel.SplashScreenViewModel

@Composable
fun SplashScreen(
    modifier: Modifier,
    onNavigateToUsersScreen:()->Unit,
){
    val viewModel = hiltViewModel<SplashScreenViewModel>()
    val errorMessage = viewModel.errorMessage.collectAsState(initial = "")
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.getJWT()
        viewModel.tokenStatus.collect { status->
            val token = status.token
            val success = status.success
            if(success || token.isEmpty()){
                async {
                    viewModel.saveJWTtoDataStore(token)
                    onNavigateToUsersScreen()
                }.await()
            } else {
                viewModel.showErrorMessage()
                Toast.makeText(context, errorMessage.value, Toast.LENGTH_SHORT).show()
            }
        }
    }

    SplashScreenComponent(modifier = modifier)
}