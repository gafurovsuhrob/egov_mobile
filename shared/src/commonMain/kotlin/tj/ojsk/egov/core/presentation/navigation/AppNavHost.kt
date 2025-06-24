package tj.ojsk.egov.core.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tj.ojsk.egov.feature.home.HomeScreen
import tj.ojsk.egov.feature.news.NewsScreen
import tj.ojsk.egov.feature.onboarding.OnboardingScreen
import tj.ojsk.egov.feature.others.OthersScreen
import tj.ojsk.egov.feature.profile.ProfileScreen
import tj.ojsk.egov.feature.services.ServicesScreen
import tj.ojsk.egov.feature.settings.SettingsScreen

@Composable
fun AppNavHost(
    completedOnboarding: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = if (completedOnboarding) Destinations.Home else Destinations.Onboarding
    ) {

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




//        composable<Destinations.AllTasks> { backStackEntry ->
//            val allTasks: Destinations.AllTasks = backStackEntry.toRoute()
//            AllTasksScreen(
//                type = allTasks.type,
//                navController = navController
//            )
//        }
//
//        composable<Destinations.AddTask> { backStackEntry ->
//            val addTask: Destinations.AddTask = backStackEntry.toRoute()
//            AddTaskScreen(
//                taskId = addTask.taskId,
//                navController = navController
//            )
//        }
//
//        composable<Destinations.Calendar> {
//            CalendarScreen(navController = navController)
//        }
//
//        composable<Destinations.Statistics> {
//            StatisticsScreen(navController = navController)
//        }
//
//        composable<Destinations.AllStatistics> {
//            AllStatisticsScreen(navController = navController)
//        }
//
//        composable<Destinations.Settings> {
//            SettingsScreen(navController = navController)
//        }
//
//        composable<Destinations.TaskProgress> { backStackEntry ->
//            val taskProgress: Destinations.TaskProgress = backStackEntry.toRoute()
//            TaskProgressScreen(
//                taskId = taskProgress.taskId,
//                navController = navController
//            )
//        }
    }
}
