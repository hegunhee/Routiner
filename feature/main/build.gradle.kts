plugins {
    id("routiner.android.feature")
}

android {
    namespace = "routiner.feature.main"
}

dependencies {

    implementation(project(":feature:daily"))
    implementation(project(":feature:insertRoutine"))
    implementation(project(":feature:record"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:repeat"))

    implementation(libs.gson)
}