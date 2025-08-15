package tj.ojsk.egov.core.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tj.ojsk.egov.feature.SearchScreen
import tj.ojsk.egov.feature.home.HomeScreen
import tj.ojsk.egov.feature.news.NewsScreen
import tj.ojsk.egov.feature.onboarding.OnboardingScreen
import tj.ojsk.egov.feature.others.OthersScreen
import tj.ojsk.egov.feature.profile.ProfileScreen
import tj.ojsk.egov.feature.services.ServicesScreen
import tj.ojsk.egov.feature.settings.SettingsScreen
import tj.ojsk.egov.feature.splash.SplashScreen

@Composable
fun AppNavHost(
    completedOnboarding: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination =
            Destinations.Splash
    ) {
        composable<Destinations.Splash> {
            SplashScreen {
                navController.navigate(if (completedOnboarding) Destinations.Home else Destinations.Onboarding) {
                    popUpTo(Destinations.Splash) { inclusive = true }
                }
            }
        }

        composable<Destinations.Onboarding> {
            OnboardingScreen(
                navController = navController
            )
        }

        composable<Destinations.Home> {
            HomeScreen(
                navController = navController
            )
        }

        composable<Destinations.Services> {
            ServicesScreen(
                navController = navController
            )
        }

        composable<Destinations.Profile> {
            ProfileScreen(
                navController = navController
            )
        }

        composable<Destinations.Others> {
            OthersScreen(
                navController = navController
            )
        }

        composable<Destinations.Settings> {
            SettingsScreen(
                navController = navController
            )
        }

        composable<Destinations.News> {
            NewsScreen(
                navController = navController
            )
        }

        composable<Destinations.Search>{
            SearchScreen(
                navController = navController
            )
        }
    }
}
