package com.example.githubtrendingprojects.model

data class ProjectDetails(
    val name: String,
    val owner: User,
    val description: String?,
    val stargazerCount: Int,
    val forkCount: Int,
    val issuesCount: Int,
    val pullRequestsCount: Int,
    val discussionsCount: Int,
    val releasesCount: Int,
    val mentionableUsers: List<User>,
    val mentionableUsersCount: Int,
    val languages: List<Language>,
    val homepageUrl: String?,
    val url: String,
)

data class Language(val name: String, val color: String?)

val kotlinProjectDetails = ProjectDetails(
    name = "kotlin",
    owner = User(
        login = "JetBrains",
        avatarUrl = "https://avatars.githubusercontent.com/u/878437?s=200&v=4",
    ),
    description = "The Kotlin Programming Language.",
    stargazerCount = 40754,
    forkCount = 5031,
    issuesCount = 0,
    pullRequestsCount = 4743,
    discussionsCount = 0,
    releasesCount = 181,
    mentionableUsers = listOf(
        User(
            login = "ikegam",
            avatarUrl = "https://avatars.githubusercontent.com/u/9870?v=4"
        ),
        User(
            login = "stepancheg",
            avatarUrl = "https://avatars.githubusercontent.com/u/28969?u=184cec698ead4d3444236763bb8bba1c3e778094&v=4"
        ),
        User(
            login = "jstrachan",
            avatarUrl = "https://avatars.githubusercontent.com/u/30140?u=8bd82c03bf74ae3694bd81116f54b949c2b7c131&v=4"
        ),
        User(
            login = "chocolateboy",
            avatarUrl = "https://avatars.githubusercontent.com/u/31533?u=8691717553640d05f8208bd31d5ca10f670e3fe0&v=4"
        ),
        User(
            login = "nd",
            avatarUrl = "https://avatars.githubusercontent.com/u/38202?v=4"
        )
    ),
    mentionableUsersCount = 566,
    languages = listOf(
        Language(name = "Kotlin", color = "#A97BFF"),
        Language(name = "Java", color = "#b07219"),
        Language(name = "Dockerfile", color = "#384d54"),
        Language(name = "Swift", color = "#F05138"),
        Language(name = "C++", color = "#f34b7d"),
    ),
    homepageUrl = "https://kotlinlang.org",
    url = "https://github.com/JetBrains/kotlin"
)