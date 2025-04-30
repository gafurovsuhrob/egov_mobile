package tj.ojsk.egov.core.presentation.navigation

import org.jetbrains.compose.resources.DrawableResource


enum class BottomNavigation(
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val index: Int,
    val route: Any
){

}