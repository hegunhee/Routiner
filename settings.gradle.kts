pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Routiner"
include (":app")
include(":core:domain")
include(":core:model")
include(":core:data")
include(":feature:main")
include(":feature:setting")
include(":feature:daily")
include(":feature:record")
include(":feature:repeat")
include(":feature:common")
include(":core:util")
include(":core:navigation")
include(":feature:insertRoutine")
include(":core:model")
include(":core:designsystem")
include(":core:ui")
