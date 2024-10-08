package domain.usecases

import domain.models.ValidationResult

class ValidateEmploymentPositionUseCase {

    fun isPositionIdValid(
        id:Int
    ):ValidationResult{

        if(id < 0){
            return ValidationResult(
                successful = false,
                errorMessage = "Position is required"
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }

}