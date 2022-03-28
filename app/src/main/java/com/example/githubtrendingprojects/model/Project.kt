package com.example.githubtrendingprojects.model

data class Project(
    val name: String,
    val nameWithOwner: String,
    val description: String?,
    val stargazerCount: Int,
    val primaryLanguage: String?,
    val owner: User,
)

val kotlinProject = Project(
    name = "kotlin",
    owner = User(
        login = "JetBrains",
        avatarUrl = "https://avatars.githubusercontent.com/u/878437?s=200&v=4",
    ),
    stargazerCount = 40754,
    primaryLanguage = "Kotlin",
    nameWithOwner = "JetBrains/kotlin",
    description = "The Kotlin Programming Language.",
)

val architectureSamplesProject = Project(
    name = "android",
    owner = User(
        login = "android",
        avatarUrl = "https://avatars.githubusercontent.com/u/32689599?s=200&v=4",
    ),
    stargazerCount = 40386,
    primaryLanguage = "Kotlin",
    nameWithOwner = "android/architecture-samples",
    description = "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.",
)

val composeSamplesProject = Project(
    name = "android",
    owner = User(
        login = "android",
        avatarUrl = "https://avatars.githubusercontent.com/u/32689599?s=200&v=4",
    ),
    stargazerCount = 11122,
    primaryLanguage = "Kotlin",
    nameWithOwner = "android/compose-samples",
    description = "Official Jetpack Compose samples.",
)