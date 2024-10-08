package destinations

import androidx.annotation.DrawableRes

enum class BottomBarDestinations(
    val id: Int,
    val title: String,
    val routes: String,
    @DrawableRes val icon: Int
) {

    USERS_SCREEN(
        id = 0,
        title = "Users",
        routes = "UsersScreen",
        icon = com.example.uikit.R.drawable.unselected_people
    ),

    SIGN_UP_SCREEN(
      id = 1,
      title = "Sign up",
      routes = "SignUpScreen",
      icon = com.example.uikit.R.drawable.unselected_person_with_plus_icon
    )

}