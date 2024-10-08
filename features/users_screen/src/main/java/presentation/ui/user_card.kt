package presentation.ui
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import compose_ui.colors.grey

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    photoUrl:String,
    name:String,
    position:String,
    email:String,
    phone:String
){
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "User profile image",
                modifier = modifier.clip(
                    CircleShape
                ).size(50.dp)
            )
            Spacer(modifier = modifier.width(16.dp))

            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = position,
                    color = grey,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
}