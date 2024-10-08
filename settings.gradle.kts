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

rootProject.name = "AbzAgencyTestTask"
include(":app")
include(":core")
include(":features:sign_up")
include(":core:uikit")
include(":features:splash")
include(":features:connectivityScreens")
include(":features:abz-api")
include(":features:datastore-api")
include(":features:users_screen")
include(":features:auth_status_screens")
