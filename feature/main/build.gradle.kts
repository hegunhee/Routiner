plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.example.main"
}

dependencies {

    implementation(project(":feature:common"))
    implementation(project(":feature:daily"))
    implementation(project(":feature:insertRoutine"))

    implementation(libs.gson)
}