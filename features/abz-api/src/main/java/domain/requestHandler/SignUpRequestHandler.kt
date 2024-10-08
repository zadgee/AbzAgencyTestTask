package domain.requestHandler

sealed class SignUpRequestHandler {
    data class Success(val successMessage:String):SignUpRequestHandler()
    data class Error(val errorMessage:String):SignUpRequestHandler()
}