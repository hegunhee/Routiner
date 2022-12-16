plugins {
    id("android.application")
}

android {
    defaultConfig {
        applicationId = "com.hegunhee.routiner"
        versionCode = (3)
        versionName = "1.2"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}
dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature"))
    implementation(project(":feature:main"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:daily"))
    implementation(project(":feature:record"))
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
}