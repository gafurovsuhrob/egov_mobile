package tj.ojsk.egov.feature.services

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import tj.ojsk.egov.core.data.utils.koinViewModel
import tj.ojsk.egov.core.presentation.component.EGovTopAppBar
import tj.ojsk.egov.feature.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(
    navController: NavController,
    viewModel: ServicesViewModel = koinViewModel(),
) {
    Scaffold(
        topBar = {
            EGovTopAppBar {
                Text(text = "Услуги")
            }
        },
    ) {

    }
}