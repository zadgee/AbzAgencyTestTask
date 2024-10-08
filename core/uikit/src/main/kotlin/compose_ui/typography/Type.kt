package compose_ui.typography

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uikit.R

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.nunito_sans_regular)
        ),
        lineHeight = 24.sp,
        fontSize = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.nunito_sans_regular)
        ),
        lineHeight = 24.sp,
        fontSize = 18.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(
           Font(R.font.nunito_sans_regular)
        ),
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.nunito_sans_regular)
        ),
        lineHeight = 20.sp,
        fontSize = 14.sp
    )
)