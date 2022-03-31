# Mobile - CI/CD - Android

What's Included in this Project? 📦

✅  [GitHub Flow](https://docs.github.com/en/get-started/quickstart/github-flow) using Pull Requests & Squash-Merge \
    Generally, we protect `master` branch [this way](https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/defining-the-mergeability-of-pull-requests/about-protected-branches). \
    For now, protected `master` branch push using `git config branch.master.pushRemote no_push`

✅  Continuous Integration - Lint, Unit Test, Integration Test, and Build using GitHub Actions \
    We can add more tools like sonar cube, danger, code coverage, screenshots etc 

✅  Continuous Deployment - Deploy App to Firebase App Distribution using Fastlane on Github Actions \
    Notify Internal Testing Team with Specified Release Notes \
    We can add any platform's deployment workflow including Google Play. \
    To Join Internal Testing Team on Firebase App Distribution - [Click Here](https://appdistribution.firebase.dev/i/b104f9f45f7fd860)

✅  Android Jetpack & Modern Android Development Practices

✅  Kotlin - Project completely written in Kotlin

✅  API - GitHub's GraphQL API using Apollo Client's Code Generation

✅  Repository Design Pattern

✅  Architecture - MVVM(Model-View-ViewModel)

✅  Dependency Injection - [Koin](https://insert-koin.io/)

✅  JetPack Compose - Modern Toolkit for Native UI Development

✅  Software Engineering Principles - DRY, SOLID etc

✅  UI/UX - Supports different mobile devices \
    We can easily add support for tablet and other devices

✅  Internationalization Support - Used string resources \
    We can easily add this any language support just by adding translations

✅  Build Configurations - Gradle & local.properties for BuildConfig for URL & GitHub Access Token

🔜  GitHub Secrets - Credentials & Configurations \
    Due to limited access on repository, used workflow specific environment variables

🔜  CI/CD Pipeline Optimization using Build only when needed & caches

🔜  GitHub/Jira Issues based Workflow

🔜  Build Flavors - Multiple flavor support for development, staging, and production

🔜  Logging 

🔜  Analytics

🔜  Crash Reporting Tools

🔜  Testing - Unit and Integration Tests with good code coverage

🔜  Semantic Versioning to Version Code Generation 

🔜  Deployment - Google Play Alpha/Beta/Production Workflow

🔜  More Application Features

🔜  Shared Element Transitions & Animations

🔜  Multi-Module Architecture 

🔜  Kotlin Multi-platform Mobile

🔜  Many more things to come 

---- 
## How to Run

1) Clone & Open Project In Android Studio
2) Copy below ENV variables on `local.properties` on project's root directory
```
API_SERVER_URL=https://api.github.com/graphql
API_ACCESS_TOKEN=YOUR_GITHUB_ACCESS_TOKEN
```
[How to create GitHub's personal access token?](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)
3) Run the app



# Xapo test project
This is a project used to evaluate candidate's coding skills and Android knowledge.

## Description
The project's idea is to list trending projects from Github and then tap on one of them and show their details.

**Requirements**
- Write your application in Kotlin
- Ensure your application looks good on different screen sizes and densities
- Ensure your application supports Android API 23+
- Use good architecture and design patterns
- Use valuable external libraries that you are used to
- Use reactive programming

**Bonus** 
- Filtering and ordering functionalities
- Unit tests
- Shared element transition or other animations (but only if they are functional to the user experience)

If there is something not specified, please be free to decide on it. 
Let us know if you need something or you have any doubt about the project.

# Hand over

Whenever you are done, just create a release in github or contact HR!

