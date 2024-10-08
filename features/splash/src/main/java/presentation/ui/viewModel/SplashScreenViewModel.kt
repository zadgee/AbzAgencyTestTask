package presentation.ui.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.models.TokenStatus
import domain.repo.SplashScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val splashScreenRepository: SplashScreenRepository,
):ViewModel() {

    private val _tokenStatus = MutableStateFlow(TokenStatus(
        success = false,
        token = ""
    ))
    val tokenStatus = _tokenStatus.asStateFlow()
    private val _errorMessage = MutableSharedFlow<String>(
        replay = 1
    )
    val errorMessage = _errorMessage.asSharedFlow()



    fun getJWT(){
        viewModelScope.launch(Dispatchers.IO) {
            val jwtStatus = splashScreenRepository.getJWT()
            _tokenStatus.emit(
                TokenStatus(
                    success = jwtStatus.success,
                    token = jwtStatus.token
                )
            )
        }
    }

    fun showErrorMessage(){
        viewModelScope.launch {
            _errorMessage.emit("Something went wrong, try again")
        }
    }

    suspend fun saveJWTtoDataStore(token:String){
        splashScreenRepository.saveJWTtoDataStore(token)
    }

}