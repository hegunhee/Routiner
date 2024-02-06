plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.example.main"
}

dependencies {

    implementation(project(":feature:common"))

    implementation(libs.gson)
}