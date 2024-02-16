plugins {
    id("routiner.android.application")
}

android {
    namespace = "com.hegunhee.routiner"
    defaultConfig {
        targetSdk = 33
        applicationId = "com.hegunhee.routiner"
        versionCode = (6)
        versionName = "1.3.2"
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:main"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:daily"))
    implementation(project(":feature:record"))
    implementation(project(":feature:repeat"))
    implementation(project(":feature:insertRoutine"))
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
}