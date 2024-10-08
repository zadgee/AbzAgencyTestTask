package presentation.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import compose_ui.colors.blue
import presentation.viewModel.UsersScreenViewModel



@Composable
fun UsersScreenComponent(
    modifier: Modifier,
    viewModel:UsersScreenViewModel
){

    val usersData = viewModel.pagingData.collectAsLazyPagingItems()


    if(usersData.loadState.refresh == LoadState.Loading){
        CircularProgressIndicator(
            color = blue
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
   ) {
       items(usersData.itemCount){ index->
           val item = usersData[index]
           item?.let {
               UserCard(
                   modifier = modifier,
                   photoUrl = item.photo,
                   name = item.name,
                   position = item.position,
                   email = item.email,
                   phone = item.phone
               )
           } ?: NoUsersFoundYet(modifier = modifier)
       }
   }

}


@Composable
fun NoUsersFoundYet(
    modifier: Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = com.example.uikit.R.drawable.users_image
            ), contentDescription = "This image will be displayed if users will be empty"
        )
        Text(
            text = "There are no users yet",
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}