package data.api
import data.responses.EmploymentPositionsResponse
import data.responses.TokenResponse
import data.responses.UsersResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface AbzAPI {

    @GET("token")
    suspend fun getToken(): Response<TokenResponse>

    @GET("users")
    suspend fun getUsers(
        @Query("page")page:Int,
        @Query("count")count:Int
    ): Response<UsersResponse>

    @POST("users")
    @Multipart
    suspend fun signUpRequest(
        @Part("token") token:String,
        @Part("name") name:String,
        @Part("email") email:String,
        @Part("phone") phone:String,
        @Part("position_id") positionId:Int,
        @Part photo:MultipartBody.Part
    ):Response<ResponseBody>

    @GET("positions")
    suspend fun getEmploymentPositions():Response<EmploymentPositionsResponse>
}