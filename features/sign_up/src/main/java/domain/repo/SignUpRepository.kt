package domain.repo
import domain.models.EmploymentPositionsMock
import domain.models.SignUpEvent
import java.io.File

interface SignUpRepository {
    suspend fun getJWT():String
    suspend fun signUp(
        token:String,
        name:String,
        email:String,
        phone:String,
        positionId:Int,
        photo:File
    ):SignUpEvent
    suspend fun getPositions():EmploymentPositionsMock
}