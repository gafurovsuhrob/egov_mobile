package tj.ojsk.egov.feature.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tj.ojsk.egov.core.presentation.component.CategoryItem
import tj.ojsk.egov.core.presentation.component.LoadingDialog
import tj.ojsk.egov.core.presentation.component.TopAppBar
import tj.ojsk.egov.core.presentation.navigation.Destinations
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.platform.Logger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(
    navController: NavController,
    viewModel: ServicesViewModel = koinViewModel(),
) {
    val categories by viewModel.categoryList.collectAsState()
    val loadingDialogState by viewModel.loadingDialogState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCategories()
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(text = "Услуги") },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(Destinations.Search)
                            }
                        ) {
                            Icon(Icons.Default.Search, contentDescription = "Поиск")
                        }
                    }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = paddingValues.calculateTopPadding() + 6.dp,
                bottom = paddingValues.calculateBottomPadding() + 6.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = { onCategoryClick(it.id) }
                )
            }
        }
    }

    LoadingDialog(
        state = loadingDialogState,
        onDismiss = { viewModel.dismissDialog() }
    )
}

fun onCategoryClick(categoryId: Int) {
    Logger.d("Category clicked", "Category id: $categoryId")
}