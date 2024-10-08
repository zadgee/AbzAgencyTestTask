package domain.models

data class EmploymentPositionsMock(
    val positions:List<EmploymentPositionMock>
)

data class EmploymentPositionMock(
    val id:Int,
    val name:String
)