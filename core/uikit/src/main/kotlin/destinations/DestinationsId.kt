package destinations

sealed class DestinationsId(val id:String){
    data object SplashScreen:DestinationsId("SplashScreen")
    data object SignUpSuccess:DestinationsId("SignUpSuccess/{successMessage}")
    data object BottomBar:DestinationsId("BottomBar")
    data object SignUpFailed:DestinationsId("SignUpFailed/{error}")
}