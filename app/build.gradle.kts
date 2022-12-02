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

}