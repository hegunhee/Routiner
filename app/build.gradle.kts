plugins {
    id("android.application")
}

android {
    defaultConfig {
        applicationId = "com.hegunhee.routiner"
        versionCode = (3)
        versionName = "1.2"
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
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
}