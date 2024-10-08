package domain.models

sealed class SignUpEvent {
    data class Success(val successMessage:String):SignUpEvent()
    data class Error(val errorMessage:String):SignUpEvent()
}