package domain.models
import java.io.File

data class UserDataValidation(
    val name:String="",
    val nameError:String?=null,
    val email:String="",
    val emailError:String?=null,
    val phone:String="",
    val phoneError:String?=null,
    val photoFile:File,
    val photoError:String?=null,
    val positionId:Int=-1,
    val positionIdError:String?=null,
    val successfulValidation:Boolean=false
)