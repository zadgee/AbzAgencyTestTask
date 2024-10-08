package presentation.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import compose_ui.colors.black
import compose_ui.colors.blue
import compose_ui.colors.disabledButtonColor
import compose_ui.colors.grey
import compose_ui.colors.red
import compose_ui.colors.white
import compose_ui.colors.yellow
import domain.models.EmploymentPositionsMock
import domain.models.UserDataValidation
import java.io.File

@Composable
fun SignUpScreenComponent(
    modifier: Modifier,
    fieldsState: UserDataValidation,
    employmentPositionsState: EmploymentPositionsMock,
    onNameChanged:(String)->Unit,
    onEmailChanged:(String)->Unit,
    onPhoneChanged:(String)->Unit,
    onUploadClick:()->Unit,
    onSignUpButtonClick:()->Unit,
    onEmploymentPositionCheck:(Int)->Unit,
){
    val email = fieldsState.email
    val name = fieldsState.name
    val phone = fieldsState.phone
    val photo = fieldsState.photoFile
    val selectedPosition = remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = modifier
        ){
            OutlinedTextField(
                modifier = modifier.padding(top = 15.dp),
                value = name,
                onValueChange = onNameChanged,
                placeholder = {
                    Text(
                        text = "Your name",
                        color = grey,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                label = {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                isError = fieldsState.nameError != null,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = blue,
                    focusedPlaceholderColor = blue,
                    focusedContainerColor = white,
                    focusedIndicatorColor = blue,
                    unfocusedContainerColor = white,
                    errorLabelColor = red,
                    errorIndicatorColor = red,
                    errorPlaceholderColor = red,
                    errorContainerColor = white
                ),
            )

            if(fieldsState.nameError != null){
                Text(
                    text = fieldsState.nameError,
                    color = red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = modifier.height(20.dp))

            OutlinedTextField(
                modifier = modifier.align(Alignment.CenterHorizontally),
                value = email,
                onValueChange = onEmailChanged,
                placeholder = {
                    Text(
                        text = "Email",
                        color = grey,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                label = {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                isError = fieldsState.emailError != null,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = blue,
                    focusedPlaceholderColor = blue,
                    focusedContainerColor = white,
                    focusedIndicatorColor = blue,
                    unfocusedContainerColor = white,
                    errorLabelColor = red,
                    errorIndicatorColor = red,
                    errorPlaceholderColor = red,
                    errorContainerColor = white
                ),
            )

            if(fieldsState.emailError != null){
                Text(
                    text = fieldsState.emailError,
                    color = red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = modifier.height(25.dp))

            OutlinedTextField(
                modifier = modifier.align(Alignment.CenterHorizontally),
                value = phone,
                onValueChange = onPhoneChanged,
                placeholder = {
                    Text(
                        text = "Phone",
                        color = grey,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                label = {
                    Text(
                        text = "Phone",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                isError = fieldsState.phoneError != null,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = blue,
                    focusedPlaceholderColor = blue,
                    focusedContainerColor = white,
                    focusedIndicatorColor = blue,
                    unfocusedContainerColor = white,
                    errorLabelColor = red,
                    errorIndicatorColor = red,
                    errorPlaceholderColor = red,
                    errorContainerColor = white
                ),
            )

            if(fieldsState.phoneError != null){
                Text(
                    text = fieldsState.phoneError,
                    color = red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if(employmentPositionsState.positions.isNotEmpty()){
                Column(
                    modifier = modifier.padding(top = 25.dp),
                ) {
                    Text(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        text = "Select your position",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    employmentPositionsState.positions.forEach{ position->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            RadioButton(
                                selected = selectedPosition.intValue == position.id,
                                onClick = {
                                    selectedPosition.intValue = position.id
                                    onEmploymentPositionCheck(
                                        selectedPosition.intValue
                                    )
                                          },
                                colors = RadioButtonColors(
                                    selectedColor = blue,
                                    unselectedColor = blue,
                                    disabledUnselectedColor = white,
                                    disabledSelectedColor = white
                                )
                            )
                            Text(
                                text = position.name,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if(fieldsState.positionIdError != null){
                            Text(
                                text = fieldsState.positionIdError,
                                color = red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            } else {
                Text(text = "No positions found")
            }

            Spacer(modifier = modifier.height(20.dp))

            OutlinedTextField(
                modifier = modifier.align(Alignment.CenterHorizontally),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Upload your photo",
                        color = grey,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                label = {
                    Text(
                        text = "Upload your photo",
                        color = grey,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                isError = fieldsState.photoError != null,
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = blue,
                    focusedPlaceholderColor = blue,
                    focusedContainerColor = white,
                    focusedIndicatorColor = blue,
                    unfocusedContainerColor = white,
                    errorLabelColor = red,
                    errorIndicatorColor = red,
                    errorPlaceholderColor = red,
                    errorContainerColor = white
                ),
                trailingIcon = {
                    Text(
                        text = "Upload",
                        color = blue,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                            .padding(end = 10.dp)
                            .clickable {
                                onUploadClick()
                            }
                    )
                },
                readOnly = true
            )

            if(fieldsState.photoError != null){
                Text(
                    text = fieldsState.photoError,
                    color = red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = modifier.height(30.dp))

            Button(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                onClick = onSignUpButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = yellow,
                    disabledContainerColor = disabledButtonColor,
                    contentColor = black,
                    disabledContentColor = grey,
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Sign up",
                    color = black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            }
    }
}