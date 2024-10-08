package data.responses

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("success")
    val isSuccessful:Boolean,
    val token:String
)
