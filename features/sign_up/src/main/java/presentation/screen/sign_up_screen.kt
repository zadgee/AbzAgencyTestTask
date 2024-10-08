package presentation.screen
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import domain.models.SignUpEvent
import domain.models.UserDataEvent
import kotlinx.coroutines.async
import presentation.viewModel.SignUpViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier,
    navigateToSuccessStatusScreen:(successMessage:String)->Unit,
    navigateToErrorStatusScreen:(errorMessage:String)->Unit,
){
    val viewModel = hiltViewModel<SignUpViewModel>()
    val fieldsState = viewModel.fieldsState
    val employmentPositionsState = viewModel.positionsState.collectAsState()
    val token = viewModel.tokenFlow.collectAsState(initial = "")
    var photoUri: Uri? by remember { mutableStateOf(Uri.EMPTY) }
    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri->
        photoUri = uri
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getJWT()
        async {
            viewModel.getPositions()
        }.await()
    }

    LaunchedEffect(key1 = fieldsState) {
        if(fieldsState.successfulValidation){
            val state = viewModel.signUp(
                token = token.value,
                name = fieldsState.name,
                email = fieldsState.email,
                phone = fieldsState.phone,
                positionId = fieldsState.positionId,
                uri = photoUri ?: Uri.EMPTY
            )
            when(state){
                is SignUpEvent.Error -> {
                    navigateToErrorStatusScreen(state.errorMessage)
                }
                is SignUpEvent.Success -> {
                    navigateToSuccessStatusScreen(state.successMessage)
                }
            }
        }
    }

    SignUpScreenComponent(
        modifier = modifier,
        fieldsState = fieldsState,
        employmentPositionsState = employmentPositionsState.value,
        onNameChanged = {viewModel.checkEvent(UserDataEvent.OnNameChanged(it))},
        onEmailChanged = {viewModel.checkEvent(UserDataEvent.OnEmailChanged(it))},
        onPhoneChanged = {viewModel.checkEvent(UserDataEvent.OnPhoneChanged(it))},
        onUploadClick = {
            pickerLauncher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
            viewModel.checkEvent(UserDataEvent.OnPhotoChanged(
                uri = photoUri?: Uri.EMPTY
            ))
        },
        onSignUpButtonClick = {
             viewModel.checkEvent(UserDataEvent.UserEnteredAllData)
        },
        onEmploymentPositionCheck = {viewModel.checkEvent(UserDataEvent.OnEmploymentPositionChanged(it))}
        )
}
