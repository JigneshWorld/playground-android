package com.example.githubtrendingprojects.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubtrendingprojects.ui.screens.popular_projects.PopularProjectsScreen
import com.example.githubtrendingprojects.ui.screens.project_details.ProjectDetailsScreen
import com.example.githubtrendingprojects.ui.screens.project_details.ProjectDetailsViewModel
import org.koin.androidx.compose.getViewModel

object Keys {
    const val Owner = "owner"
    const val Name = "name"
}

object Routes {

    const val Projects = "projects"
    const val ProjectDetails = "project_details/{${Keys.Owner}}/{${Keys.Name}}"

    fun projectDetails(owner: String, name: String) = "project_details/$owner/$name"

}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Projects) {
        composable(Routes.Projects) {
            PopularProjectsScreen(
                onProjectClick = { name, owner ->
                    navController.navigate(Routes.projectDetails(owner = owner, name = name))
                }
            )
        }

        composable(
            Routes.ProjectDetails,
            arguments = listOf(
                navArgument(Keys.Owner) { type = NavType.StringType },
                navArgument(Keys.Name) { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val owner = backStackEntry.arguments?.getString(Keys.Owner)
            val name = backStackEntry.arguments?.getString(Keys.Name)
            if (owner != null && name != null) {
                val viewModel: ProjectDetailsViewModel = getViewModel()
                viewModel.fetchProjectDetails(owner = owner, name = name)
                ProjectDetailsScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}