package presentation.error_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(
    modifier: Modifier,
    navigateBack:()->Unit,
    message:String
){
    ErrorScreenComponent(
        modifier = modifier,
        navigateBack = navigateBack,
        message = message
    )
}