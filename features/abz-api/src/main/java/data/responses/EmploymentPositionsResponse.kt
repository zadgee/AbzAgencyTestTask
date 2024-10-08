package data.responses

import com.google.gson.annotations.SerializedName

data class EmploymentPositionsResponse(
    val positions:List<PositionResponse>
)

data class PositionResponse(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String
)
