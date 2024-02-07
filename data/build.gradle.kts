plugins {
    id("routiner.android.library")
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:util"))
    
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
}