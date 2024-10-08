package domain.usecases

import domain.models.ValidationResult
import java.util.regex.Pattern

class ValidatePhoneUseCase {

    fun isPhoneValid(phone:String): ValidationResult {
        val phoneRegex = "^\\+380\\d{9}\$"
        val pattern: Pattern = Pattern.compile(phoneRegex)
        if(!pattern.matcher(phone).matches()){
             return ValidationResult(
                 successful = false,
                 errorMessage = "Invalid phone number"
             )
         } else if(phone.isEmpty()){
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