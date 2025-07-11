package tj.ojsk.egov.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.painterResource
import tj.ojsk.egov.core.presentation.component.EGovNavigationRailBar
import tj.ojsk.egov.core.presentation.navigation.AppNavHost
import tj.ojsk.egov.core.presentation.navigation.BottomNav
import tj.ojsk.egov.core.presentation.navigation.Destinations

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    onBoardingCompleted: Boolean,
) {
    val windowSizeClass = calculateWindowSizeClass()
    val useNavRail = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Compact

    if (useNavRail) {
        Row {
            if (onBoardingCompleted) {
                EGovNavigationRailBar(
                    navController = navController
                )
            }
            AppNavHost(
                navController = navController,
                completedOnboarding = onBoardingCompleted,
            )
        }
    } else {
        Scaffold(
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars), // 👈 добавлено
            contentWindowInsets = WindowInsets.systemBars,
            content = { innerPadding ->
                AppNavHost(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    completedOnboarding = true,
                )
            },
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("?")
                    ?: Destinations.Onboarding::class.qualifiedName.orEmpty()

                BottomNavigation(
                    backgroundColor = MaterialTheme.colorScheme.background,
                ) {
                    BottomNav.entries
                        .forEach { navigationItem ->
                            val isSelected by remember(currentRoute) {
                                derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                            }
                            BottomNavigationItem(
                                modifier = Modifier
                                    .testTag(navigationItem.name)
                                    .padding(horizontal = 8.dp),
                                selected = isSelected,
                                label = {
                                    Text(
                                        text = navigationItem.label,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(if (isSelected) navigationItem.selectedIcon else navigationItem.unselectedIcon),
                                        contentDescription = navigationItem.label,
                                        tint = if (isSelected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.onBackground
                                        },
                                    )
                                },
                                onClick = {
                                    navController.navigate(navigationItem.route)
                                },
                            )
                        }
                }
            }
        )
    }
}