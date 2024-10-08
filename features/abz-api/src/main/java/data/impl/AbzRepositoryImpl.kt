package data.impl
import android.graphics.Bitmap
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import data.api.AbzAPI
import data.responses.UserModel
import domain.dto.EmploymentPositionsDTO
import domain.repo.AbzRepository
import domain.dto.TokenDTO
import domain.mapper.toEmploymentPositionsDTO
import domain.mapper.toTokenDTO
import data.usersSource.UsersPagingSource
import domain.requestHandler.SignUpRequestHandler
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject

class AbzRepositoryImpl @Inject constructor(
    private val api:AbzAPI,
): AbzRepository {

    override suspend fun getToken(): TokenDTO {
       return api.getToken().body()?.toTokenDTO()?:TokenDTO(isSuccessful = false, token = "")
    }

    override suspend fun signUpRequest(
        token: String,
        name: String,
        email: String,
        phone: String,
        positionId: Int,
        photo:File
    ): SignUpRequestHandler {
        return try {

            val request = api.signUpRequest(
                token = token,
                name = name,
                email = email,
                phone = phone,
                positionId = positionId,
                photo = MultipartBody.Part.createFormData(
                    name = "photo",
                    filename = photo.name,
                    body = photo.asRequestBody()
                )
            )
            if(request.isSuccessful){
                SignUpRequestHandler.Success(request.message())
            } else if (request.code() == 401){
                SignUpRequestHandler.Error("Invalid token")
            } else {
                SignUpRequestHandler.Error(request.message())
            }
        } catch (e:Exception){
            Log.d("TAG","exception:${e.message}")
            SignUpRequestHandler.Error(e.message?:"Something went wrong")
        } catch (e:HttpException){
            SignUpRequestHandler.Error(e.message?:"Something went wrong with http request")
        }
    }

    override suspend fun getEmploymentPositions(): EmploymentPositionsDTO? {
        return try {
            val response = api.getEmploymentPositions().body()
            response?.toEmploymentPositionsDTO()
        } catch (e:Exception){
            EmploymentPositionsDTO(positions = emptyList())
        }
    }

    override fun usersPagingSource(): Pager<Int, UserModel> {
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = { UsersPagingSource(api) }
        )
    }
}