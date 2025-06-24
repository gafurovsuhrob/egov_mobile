package tj.ojsk.egov.core.presentation.navigation

import egov.shared.generated.resources.Res
import egov.shared.generated.resources.home_filled
import egov.shared.generated.resources.home_outlined
import egov.shared.generated.resources.others_filled
import egov.shared.generated.resources.others_outlined
import egov.shared.generated.resources.profile_filled
import egov.shared.generated.resources.profile_outlined
import egov.shared.generated.resources.services_filled
import egov.shared.generated.resources.services_outlined
import org.jetbrains.compose.resources.DrawableResource


enum class BottomNav(
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val index: Int,
    val route: Any
){
    Main(
        label = "Главная",
        selectedIcon = Res.drawable.home_filled,
        unselectedIcon = Res.drawable.home_outlined,
        route = Destinations.Home,
        index = 0,
    ),
    Services(
        label = "Услуги",
        selectedIcon = Res.drawable.services_filled,
        unselectedIcon = Res.drawable.services_outlined,
        route = Destinations.Services,
        index = 1,
    ),
    Profile(
        label = "Профиль",
        selectedIcon = Res.drawable.profile_filled,
        unselectedIcon = Res.drawable.profile_outlined,
        route = Destinations.Profile,
        index = 2,
    ),
    Others(
        label = "Прочее",
        selectedIcon = Res.drawable.others_filled,
        unselectedIcon = Res.drawable.others_outlined,
        route = Destinations.Others,
        index = 3,
    ),
}

enum class NavRail(
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val index: Int,
    val route: Any
){
    Main(
        label = "Главная",
        selectedIcon = Res.drawable.home_filled,
        unselectedIcon = Res.drawable.home_outlined,
        route = Destinations.Home,
        index = 0,
    ),
    Services(
        label = "Услуги",
        selectedIcon = Res.drawable.services_filled,
        unselectedIcon = Res.drawable.services_outlined,
        route = Destinations.Services,
        index = 1,
    ),
    Profile(
        label = "Профиль",
        selectedIcon = Res.drawable.profile_filled,
        unselectedIcon = Res.drawable.profile_outlined,
        route = Destinations.Profile,
        index = 2,
    ),
    Others(
        label = "Прочее",
        selectedIcon = Res.drawable.others_filled,
        unselectedIcon = Res.drawable.others_outlined,
        route = Destinations.Others,
        index = 3,
    ),
}