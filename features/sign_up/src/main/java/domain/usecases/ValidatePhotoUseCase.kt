package domain.usecases
import domain.models.ValidationResult
import java.io.File

class ValidatePhotoUseCase {

    fun isPhotoValid(photoFile: File): ValidationResult {
        val validExtensions = listOf("jpg", "jpeg")
        val maxSizeInBytes = 5 * 1024 * 1024
        val fileExtension = photoFile.extension.lowercase()
        val photoPath = photoFile.path

        if (photoFile.length() > maxSizeInBytes) {
            return ValidationResult(
                successful = false,
                errorMessage = "Photo size must not exceed 5 MB."
            )
        }
        else if(photoPath.isEmpty()){
            return ValidationResult(
                successful = false,
                errorMessage = "Photo is required."
            )
        }
        else if (!validExtensions.contains(fileExtension)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Photo format must be JPEG or JPG."
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}