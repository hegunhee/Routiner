plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.hegunhee.repeat"
}

dependencies {

    implementation(project(":feature:common"))
    implementation(project(":core:ui"))
}