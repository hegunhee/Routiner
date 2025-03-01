plugins {
    id("routiner.android.library")
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:util"))
    implementation(project(":core:model"))
    
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
    testImplementation(libs.assertj.core)
}