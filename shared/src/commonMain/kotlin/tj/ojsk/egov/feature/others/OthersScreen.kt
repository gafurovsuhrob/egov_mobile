package tj.ojsk.egov.feature.others

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.core.logger.Logger
import tj.ojsk.egov.core.presentation.component.EGovTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OthersScreen(
    navController: NavController

) {
    Scaffold(
        topBar = {
            EGovTopAppBar {
                Text(text = "Прочее")
            }
        },
    ) { paddingValues -> // <-- добавьте этот параметр
        val sampleItems = listOf("Политика конфиденциальности", "Контакты", "Настройки", "Выход")

        // Добавим padding от Scaffold
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // <-- вот он!
        ) {
            ClickableList(
                items = sampleItems,
                onItemClick = { clickedItem ->
                    // Обработка клика
                }
            )
        }
    }
}

@Composable
fun ClickableList(
    items: List<String>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items) { index, item ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .padding(16.dp)
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Добавляем Divider, кроме последнего элемента
            if (index < items.lastIndex) {
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }
        }
    }
}
