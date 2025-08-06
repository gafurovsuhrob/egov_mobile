package tj.ojsk.egov.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.core.domain.model.TextFieldState
import tj.ojsk.egov.core.presentation.component.Button
import tj.ojsk.egov.core.presentation.component.InputTextField
import tj.ojsk.egov.core.presentation.component.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Главная")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Test(onTaskDescriptionChange = {})

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                text = "Test",
                onClick = {
                    viewModel.getCategories()
                },
            )
        }
    }
}

@Composable
private fun Test(
    onTaskDescriptionChange: (String) -> Unit,
){
    InputTextField(
        modifier = Modifier.fillMaxWidth(),
        maxLines = 5,
        label = {
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                ),
            )
        },
        value = TextFieldState(""),
        onValueChange = onTaskDescriptionChange,
        placeholder = {
            Text(
                text = "Enter Description",
                style = MaterialTheme.typography.titleSmall,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
        ),
        textStyle = MaterialTheme.typography.titleSmall.copy(
            fontSize = 16.sp,
        ),
    )
}