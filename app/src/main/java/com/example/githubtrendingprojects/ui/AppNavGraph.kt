package com.example.githubtrendingprojects.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubtrendingprojects.ui.screens.popular_projects.PopularProjectsScreen


object Routes {

    const val Projects = "projects"


}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Projects) {
        composable(Routes.Projects) {
            PopularProjectsScreen(
                onProjectClick = { _, _ ->

                }
            )
        }
    }
}