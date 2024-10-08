package presentation.viewModel
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.models.EmploymentPositionsMock
import domain.models.SignUpEvent
import domain.models.UserDataEvent
import domain.models.UserDataValidation
import domain.repo.SignUpRepository
import domain.usecases.ConvertFileToUriUseCase
import domain.usecases.ValidateEmailUseCase
import domain.usecases.ValidateEmploymentPositionUseCase
import domain.usecases.ValidateNameUseCase
import domain.usecases.ValidatePhoneUseCase
import domain.usecases.ValidatePhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository,
    private val convertFileToUriUseCase: ConvertFileToUriUseCase
):ViewModel() {

    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePhoneUseCase = ValidatePhoneUseCase()
    private val validatePhotoUseCase = ValidatePhotoUseCase()
    private val validateNameUseCase = ValidateNameUseCase()
    private val validateEmploymentPosition = ValidateEmploymentPositionUseCase()

    private val _positionsState = MutableStateFlow(
        EmploymentPositionsMock(
            positions = emptyList()
        )
    )
    val positionsState = _positionsState.asStateFlow()

    private val _tokenFlow = MutableSharedFlow<String>()
    val tokenFlow = _tokenFlow.asSharedFlow()

    var fieldsState by mutableStateOf(UserDataValidation(
        photoFile = convertFileToUriUseCase.execute(
            uri = Uri.EMPTY
        )
    ))
        private set

    fun getJWT(){
        viewModelScope.launch {
            val jwt = repository.getJWT()
            _tokenFlow.emit(jwt)
        }
    }

     suspend fun signUp(
        token:String,
        name:String,
        email:String,
        phone:String,
        positionId:Int,
        uri:Uri
    ):SignUpEvent = withContext(Dispatchers.IO){
            val photo = convertFileToUriUseCase.execute(
                uri
            )
            repository.signUp(
               token = token,
               name = name,
               email = email,
               phone = phone,
               positionId = positionId,
               photo = photo
           )
    }

    fun checkEvent(event: UserDataEvent){
        when(event){
            is UserDataEvent.OnNameChanged -> {
                fieldsState = fieldsState.copy(
                    name = event.name,
                )
            }
            is UserDataEvent.OnEmailChanged -> {
                fieldsState = fieldsState.copy(
                    email = event.email,
                )
            }
            is UserDataEvent.OnPhoneChanged -> {
                fieldsState = fieldsState.copy(
                    phone = event.phone,
                )
            }
            is UserDataEvent.OnPhotoChanged -> {
                fieldsState = fieldsState.copy(
                    photoFile = convertFileToUriUseCase.execute(
                        uri = event.uri
                    ),
                )
            }
            is UserDataEvent.UserEnteredAllData -> submitData()

            is UserDataEvent.OnEmploymentPositionChanged -> {
                fieldsState = fieldsState.copy(
                    positionId = event.id
                )
            }
        }
    }

     fun getPositions(){
         viewModelScope.launch {
             val list = repository.getPositions()
             _positionsState.emit(
                 list
             )
         }
    }

    private fun submitData(
    ):Boolean{
        val enteredName = validateNameUseCase.isNameValid(
            name = fieldsState.name
        )
        val enteredEmail = validateEmailUseCase.isEmailValid(
            email = fieldsState.email
        )
        val enteredPhone = validatePhoneUseCase.isPhoneValid(
            phone = fieldsState.phone
        )
        val enteredPhoto = validatePhotoUseCase.isPhotoValid(
            photoFile = fieldsState.photoFile
        )
        val chosenEmploymentPosition = validateEmploymentPosition.isPositionIdValid(
            id = fieldsState.positionId
        )
        val hasError = listOf(
            enteredName,
            enteredEmail,
            enteredPhone,
            enteredPhoto,
            chosenEmploymentPosition
        ).any {
            !it.successful
        }
        fieldsState = if (hasError) {
            fieldsState.copy(
                nameError = enteredName.errorMessage,
                emailError = enteredEmail.errorMessage,
                phoneError = enteredPhone.errorMessage,
                photoError = enteredPhoto.errorMessage,
                positionIdError = chosenEmploymentPosition.errorMessage
            )
        } else {
            fieldsState.copy(
                nameError = null,
                emailError = null,
                phoneError = null,
                photoError = null,
                positionIdError = null,
                successfulValidation = true
            )
        }
        return hasError
    }


}