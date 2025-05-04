plugins {
    id("routiner.android.library")
    id("routiner.android.compose")
}

android {
    namespace = "routiner.core.ui"

}

dependencies {

    implementation(project(":core:model"))

}
