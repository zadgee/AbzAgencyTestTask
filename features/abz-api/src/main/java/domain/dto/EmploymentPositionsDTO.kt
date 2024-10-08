package domain.dto

data class EmploymentPositionsDTO(
    val positions:List<PositionDTO>
)

data class PositionDTO(
    val id:Int,
    val name:String
)