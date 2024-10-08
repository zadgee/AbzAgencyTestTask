package domain.usecases

import domain.models.ValidationResult

class ValidateNameUseCase {

    fun isNameValid(name:String): ValidationResult {
         if(name.length < 2){
            ValidationResult(
                successful = false,
                errorMessage = "Name must have at least 2 characters"
            )
         } else if(name.length > 60){
            ValidationResult(
                successful = false,
                errorMessage = "Name must have less than 60 characters"
            )
        } else if(name.isEmpty()){
            ValidationResult(
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