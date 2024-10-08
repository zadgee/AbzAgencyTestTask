package domain.models
import android.net.Uri

sealed class UserDataEvent {
    data class OnNameChanged(val name:String):UserDataEvent()
    data class OnEmailChanged(val email:String):UserDataEvent()
    data class OnPhoneChanged(val phone:String):UserDataEvent()
    data class OnPhotoChanged(val uri: Uri):UserDataEvent()
    data class OnEmploymentPositionChanged(val id:Int):UserDataEvent()
    data object UserEnteredAllData:UserDataEvent()
}