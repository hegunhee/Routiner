plugins {
    id("routiner.android.feature")
}

android {
    namespace = "routiner.feature.repeat"
}

dependencies {

    implementation(project(":core:ui"))
}