package domain.usecases

import domain.models.ValidationResult

class ValidateEmailUseCase {

    fun isEmailValid(email:String): ValidationResult {
        val emailRegex = Regex(
            "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$"
        )
        if(!emailRegex.matches(email)){
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid email format"
            )
        } else if(email.isEmpty()){
            return ValidationResult(
                successful = false,
                errorMessage = "Required field"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}