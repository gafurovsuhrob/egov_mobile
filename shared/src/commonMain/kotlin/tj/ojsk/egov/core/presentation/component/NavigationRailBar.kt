package tj.ojsk.egov.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.painterResource
import tj.ojsk.egov.core.presentation.navigation.BottomNavItems
import tj.ojsk.egov.core.presentation.navigation.Destinations
import tj.ojsk.egov.core.presentation.navigation.NavRailItems
import tj.ojsk.egov.core.utils.koinViewModel
import tj.ojsk.egov.feature.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NavigationRailBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val profileViewModel: ProfileViewModel = koinViewModel()
    val windowSizeClass = calculateWindowSizeClass()
    val setWeight = windowSizeClass.heightSizeClass > WindowHeightSizeClass.Compact
    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        containerColor = MaterialTheme.colorScheme.surface,
        header = {},
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("?")
            ?: Destinations.Onboarding::class.qualifiedName.orEmpty()
        val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()

        val navItems = NavRailItems.getAll(isLoggedIn)

        Column(
            modifier = Modifier.fillMaxHeight()
                .verticalScroll(state = rememberScrollState(), enabled = !setWeight)
        ) {
            navItems.forEachIndexed { index, navigationItem ->
                val isSelected by remember(currentRoute) {
                    derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                }
                if (setWeight && index == NavRailItems.getAll(isLoggedIn).size - 1) {
                    Spacer(Modifier.weight(1f))
                }
                NavigationRailItem(
                    modifier = Modifier.padding(vertical = 12.dp),
                    icon = {
                        Icon(
                            painter = painterResource(if (isSelected) navigationItem.selectedIcon else navigationItem.unselectedIcon),
                            contentDescription = navigationItem.label,
                        )
                    },
                    label = {
                        Text(
                            text = navigationItem.label,
                        )
                    },
                    alwaysShowLabel = true,
                    selected = isSelected,
                    onClick = {
                        navController.navigate(navigationItem.route)
                    },
                )
            }
        }
    }
}
