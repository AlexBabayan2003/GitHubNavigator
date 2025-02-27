pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "GitHubNavigator"
include(":app")
include(":core")
include(":app-database")

include(":feature-login:data")
include(":feature-login:domain")
include(":feature-login:presentation")

include(":feature-all-users:data")
include(":feature-all-users:domain")
include(":feature-all-users:presentation")
include(":feature-all-users:database")

include(":feature-profile:data")
include(":feature-profile:domain")
include(":feature-profile:presentation")
include(":feature-profile:database")

include(":feature-user-repos:data")
include(":feature-user-repos:domain")
include(":feature-user-repos:presentation")
include(":feature-user-repos:database")
