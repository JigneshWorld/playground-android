package com.example.githubtrendingprojects.di

import com.example.githubtrendingprojects.api.apolloClient
import com.example.githubtrendingprojects.data.projects.ProjectsRepository
import com.example.githubtrendingprojects.data.projects.impl.RemoteProjectsRepository
import com.example.githubtrendingprojects.ui.screens.popular_projects.PopularProjectsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { apolloClient() }

    single<ProjectsRepository> { RemoteProjectsRepository(get()) }

    viewModel { PopularProjectsViewModel(get()) }
}