package com.example.githubtrendingprojects.data.projects

import com.example.githubtrendingprojects.data.AppResult
import com.example.githubtrendingprojects.model.Project
import com.example.githubtrendingprojects.model.ProjectDetails

interface ProjectsRepository {
    suspend fun getPopularProjects(): AppResult<List<Project>>

    suspend fun getProjectDetails(owner: String, name: String): AppResult<ProjectDetails>
}