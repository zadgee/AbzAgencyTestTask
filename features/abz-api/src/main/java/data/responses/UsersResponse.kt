package data.responses

data class UsersResponse(
    val users:List<UserModel>
)

data class UserModel(
    val photo:String,
    val name:String,
    val position:String,
    val email:String,
    val phone:String
)