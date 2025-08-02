package tj.ojsk.egov.core.presentation.navigation

import egov.shared.generated.resources.Res
import egov.shared.generated.resources.home_filled
import egov.shared.generated.resources.home_outlined
import egov.shared.generated.resources.login_filled
import egov.shared.generated.resources.login_outlined
import egov.shared.generated.resources.others_filled
import egov.shared.generated.resources.others_outlined
import egov.shared.generated.resources.profile_filled
import egov.shared.generated.resources.profile_outlined
import egov.shared.generated.resources.services_filled
import egov.shared.generated.resources.services_outlined
import org.jetbrains.compose.resources.DrawableResource

object BottomNavItems {

    fun getAll(isLoggedIn: Boolean): List<BottomNavItem> = listOf(
        BottomNavItem(
            label = "Главная",
            selectedIcon = Res.drawable.home_filled,
            unselectedIcon = Res.drawable.home_outlined,
            route = Destinations.Home,
            index = 0,
        ),
        BottomNavItem(
            label = "Услуги",
            selectedIcon = Res.drawable.services_filled,
            unselectedIcon = Res.drawable.services_outlined,
            route = Destinations.Services,
            index = 1,
        ),
        BottomNavItem(
            label = if (isLoggedIn) "Профиль" else "Войти",
            selectedIcon = if (isLoggedIn) Res.drawable.profile_filled else Res.drawable.login_filled,
            unselectedIcon = if (isLoggedIn) Res.drawable.profile_outlined else Res.drawable.login_outlined,
            route = Destinations.Profile,
            index = 2,
        ),
        BottomNavItem(
            label = "Прочее",
            selectedIcon = Res.drawable.others_filled,
            unselectedIcon = Res.drawable.others_outlined,
            route = Destinations.Others,
            index = 3,
        )
    )
}

data class BottomNavItem(
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val route: Any,
    val index: Int
)

object NavRailItems {
    fun getAll(isLoggedIn: Boolean): List<NavRailItem> = listOf(
        NavRailItem(
            label = "Главная",
            selectedIcon = Res.drawable.home_filled,
            unselectedIcon = Res.drawable.home_outlined,
            route = Destinations.Home,
            index = 0,
        ),
        NavRailItem(
            label = "Услуги",
            selectedIcon = Res.drawable.services_filled,
            unselectedIcon = Res.drawable.services_outlined,
            route = Destinations.Services,
            index = 1,
        ),
        NavRailItem(
            label = if (isLoggedIn) "Профиль" else "Войти",
            selectedIcon = if (isLoggedIn) Res.drawable.profile_filled else Res.drawable.login_filled,
            unselectedIcon = if (isLoggedIn) Res.drawable.profile_outlined else Res.drawable.login_outlined,
            route = Destinations.Profile,
            index = 2,
        ),
        NavRailItem(
            label = "Прочее",
            selectedIcon = Res.drawable.others_filled,
            unselectedIcon = Res.drawable.others_outlined,
            route = Destinations.Others,
            index = 3,
        )
    )
}

data class NavRailItem(
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val route: Any,
    val index: Int
)