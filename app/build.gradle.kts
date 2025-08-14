plugins {
    id("routiner.android.application")
}

android {
    namespace = "com.hegunhee.routiner"
    defaultConfig {
        targetSdk = 35
        applicationId = "com.hegunhee.routiner"
        versionCode = (8)
        versionName = "1.5.0"
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":feature:main"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:daily"))
    implementation(project(":feature:record"))
    implementation(project(":feature:repeat"))
    implementation(project(":feature:insertRoutine"))
    implementation(project(":core:model"))
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)

    implementation(libs.material3)
    implementation(libs.activity.compose)
}
