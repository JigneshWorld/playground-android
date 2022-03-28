package com.example.githubtrendingprojects.data.projects.impl

import com.apollographql.apollo3.ApolloClient
import com.example.githubtrendingprojects.PopularProjectsQuery
import com.example.githubtrendingprojects.ProjectDetailsQuery
import com.example.githubtrendingprojects.data.AppResult
import com.example.githubtrendingprojects.data.projects.ProjectsRepository
import com.example.githubtrendingprojects.model.Language
import com.example.githubtrendingprojects.model.Project
import com.example.githubtrendingprojects.model.ProjectDetails
import com.example.githubtrendingprojects.model.User

class RemoteProjectsRepository(private val apolloClient: ApolloClient) : ProjectsRepository {
    override suspend fun getPopularProjects(): AppResult<List<Project>> {
        val query =
            PopularProjectsQuery(queryString = "stars:>0 sort:stars", numberOfRepository = 20)
        val response = apolloClient.query(query).execute()
        if (response.hasErrors()) {
            return AppResult.Error(Exception("Internal API Error"))
        }
        val projects = mutableListOf<Project>()

        response.data?.search?.edges?.let { edges ->
            edges.forEach { edge ->
                edge?.node?.onRepository?.let { repo ->
                    val project = Project(
                        name = repo.name,
                        nameWithOwner = repo.nameWithOwner,
                        description = repo.description,
                        stargazerCount = repo.stargazerCount,
                        primaryLanguage = repo.primaryLanguage?.name,
                        owner = User(
                            login = repo.owner.login,
                            avatarUrl = repo.owner.avatarUrl as String? ?: "",
                        ),
                    )
                    projects.add(project)
                }
            }
        }
        return AppResult.Success(projects)
    }

    override suspend fun getProjectDetails(owner: String, name: String): AppResult<ProjectDetails> {
        val query =
            ProjectDetailsQuery(owner = owner, name = name)
        val response = apolloClient.query(query).execute()
        if (response.hasErrors()) {
            return AppResult.Error(Exception("Internal API Error"))
        }

        response.data?.repository?.let { repo ->

            val mentionableUsers = mutableListOf<User>()
            val languages = mutableListOf<Language>()

            repo.mentionableUsers.edges?.forEach { edge ->
                edge?.node?.let { node ->
                    mentionableUsers.add(User(login = node.login, avatarUrl = node.avatarUrl as String? ?: ""))
                }
            }

            repo.languages?.edges?.forEach { edge ->
                edge?.node?.let { node ->
                    languages.add(Language(name = node.name, color = node.color))
                }
            }


            val projectDetails = ProjectDetails(
                name = repo.name,
                description = repo.description,
                stargazerCount = repo.stargazerCount,
                owner = User(
                    login = repo.owner.login,
                    avatarUrl = repo.owner.avatarUrl as String,
                ),
                forkCount = repo.forkCount,
                issuesCount = repo.issues.totalCount,
                pullRequestsCount = repo.pullRequests.totalCount,
                discussionsCount = repo.discussions.totalCount,
                releasesCount = repo.releases.totalCount,
                mentionableUsers =  mentionableUsers,
                mentionableUsersCount = repo.mentionableUsers.totalCount,
                languages = languages,
                homepageUrl = repo.homepageUrl as String?,
                url = repo.url as String,
            )
            return AppResult.Success(projectDetails)
        }

        return AppResult.Error(Exception("Internal Error"))
    }

}