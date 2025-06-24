package tj.ojsk.egov.feature.news

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import tj.ojsk.egov.core.data.utils.koinViewModel
import tj.ojsk.egov.core.presentation.component.EGovTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel = koinViewModel(),
) {
    Scaffold(
        topBar = {
            EGovTopAppBar {
                Text(text = "Новости")
            }
        },
    ) {

    }
}