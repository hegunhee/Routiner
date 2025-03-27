plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.hegunhee.repeat"
}

dependencies {

    implementation(project(":core:ui"))
}