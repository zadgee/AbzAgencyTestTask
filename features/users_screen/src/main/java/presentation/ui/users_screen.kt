package presentation.ui
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import presentation.viewModel.UsersScreenViewModel

@Composable
fun UsersScreen(
    modifier: Modifier
) {
    val viewModel = hiltViewModel<UsersScreenViewModel>()
     UsersScreenComponent(
         modifier = modifier, viewModel = viewModel
     )
}