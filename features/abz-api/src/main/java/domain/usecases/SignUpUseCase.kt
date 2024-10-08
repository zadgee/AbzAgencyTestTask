package domain.usecases
import domain.repo.AbzRepository
import domain.requestHandler.SignUpRequestHandler
import java.io.File
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AbzRepository
) {

    suspend fun execute(
        token:String,
        name:String,
        email:String,
        phone:String,
        positionId:Int,
        photo: File
    ):SignUpRequestHandler{
        return repository.signUpRequest(
            token = token,
            name = name,
            email = email,
            phone = phone,
            positionId = positionId,
            photo = photo
        )
    }

}