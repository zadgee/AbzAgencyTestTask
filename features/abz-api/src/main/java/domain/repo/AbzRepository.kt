package domain.repo
import androidx.paging.Pager
import data.responses.UserModel
import domain.dto.EmploymentPositionsDTO
import domain.dto.TokenDTO
import domain.requestHandler.SignUpRequestHandler
import java.io.File

interface AbzRepository {
    suspend fun getToken(): TokenDTO
    suspend fun signUpRequest(
        token:String,
        name:String,
        email:String,
        phone:String,
        positionId:Int,
        photo: File
    ): SignUpRequestHandler
    suspend fun getEmploymentPositions(): EmploymentPositionsDTO?
    fun usersPagingSource(): Pager<Int, UserModel>
}