package presentation.success_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SuccessScreen(
    modifier: Modifier,
    navigateToUsersScreen:()->Unit,
    message:String
){
    SuccessScreenComponent(
        modifier = modifier,
        navigateToUsersScreen = navigateToUsersScreen,
        message = message
    )
}
