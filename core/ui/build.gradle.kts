plugins {
    id("routiner.android.library")
    id("routiner.android.compose")
}

android {
    namespace = "hegunhee.routiner.ui"

}

dependencies {

    implementation(project(":core:model"))

}
