package com.example.githubtrendingprojects.ui.screens.project_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtrendingprojects.data.AppResult
import com.example.githubtrendingprojects.data.projects.ProjectsRepository
import com.example.githubtrendingprojects.model.*
import kotlinx.coroutines.launch


class ProjectDetailsViewModel(private val projectsRepository: ProjectsRepository) : ViewModel() {

    var uiState by mutableStateOf<ProjectDetailsUiState>(ProjectDetailsUiState.Empty)
        private set

    fun fetchProjectDetails(name: String, owner: String) {
        uiState = ProjectDetailsUiState.Loading
        viewModelScope.launch {
            val result = projectsRepository.getProjectDetails(owner = owner, name = name)
            uiState = when(result){
                is AppResult.Error -> {
                    val message = result.exception.message ?: "Internal Error"
                    ProjectDetailsUiState.Error(message)
                }
                is AppResult.Success -> {
                    ProjectDetailsUiState.Success(ProjectDetailsUiModel(result.data))
                }
            }
        }
    }

}

sealed class ProjectDetailsUiState {
    object Empty : ProjectDetailsUiState()
    object Loading : ProjectDetailsUiState()
    data class Success(val data: ProjectDetailsUiModel) : ProjectDetailsUiState()
    data class Error(val message: String) : ProjectDetailsUiState()
}

data class ProjectDetailsUiModel(val projectDetails: ProjectDetails)

val mockProjectDetailsUiModel = ProjectDetailsUiModel(kotlinProjectDetails)