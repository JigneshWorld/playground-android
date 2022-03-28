package com.example.githubtrendingprojects.ui.screens.popular_projects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtrendingprojects.data.AppResult
import com.example.githubtrendingprojects.data.projects.ProjectsRepository
import com.example.githubtrendingprojects.model.Project
import com.example.githubtrendingprojects.model.architectureSamplesProject
import com.example.githubtrendingprojects.model.composeSamplesProject
import com.example.githubtrendingprojects.model.kotlinProject
import kotlinx.coroutines.launch


class PopularProjectsViewModel(private val projectsRepository: ProjectsRepository) : ViewModel() {

    var uiState by mutableStateOf<PopularProjectsUiState>(PopularProjectsUiState.Empty)
        private set

    init {
        fetchProjects()
    }

    private fun fetchProjects() {
        uiState = PopularProjectsUiState.Loading
        viewModelScope.launch {
            val result = projectsRepository.getPopularProjects()
            uiState = when (result) {
                is AppResult.Error -> PopularProjectsUiState.Error(
                    result.exception.message ?: "Internal Error"
                )
                is AppResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        PopularProjectsUiState.Success(PopularProjectsUiModel(result.data))
                    } else {
                        PopularProjectsUiState.Empty
                    }
                }
            }
        }
    }
}

sealed class PopularProjectsUiState {
    object Empty : PopularProjectsUiState()
    object Loading : PopularProjectsUiState()
    data class Success(val data: PopularProjectsUiModel) : PopularProjectsUiState()
    data class Error(val message: String) : PopularProjectsUiState()
}

data class PopularProjectsUiModel(val projects: List<Project> = listOf())

val mockPopularProjectsUiModel = PopularProjectsUiModel(
    projects = listOf(
        kotlinProject,
        architectureSamplesProject,
        composeSamplesProject
    )
)