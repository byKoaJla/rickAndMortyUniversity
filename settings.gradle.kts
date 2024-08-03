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

rootProject.name = "R&M University"
include(":app")
include(":core:network")
include(":core:common")
include(":feature:character")
include(":ui")
include(":data:character")
include(":feature:episodes")
include(":data:character-episodes")
include(":feature:characters")
include(":data:characters")
