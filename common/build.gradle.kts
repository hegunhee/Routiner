plugins {
    id("android.feature")
}

android {
    namespace = "com.example.common"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.constraintlayout)
    implementation(libs.junit)
    androidTestImplementation(libs.testext)
    androidTestImplementation(libs.espresso)

    implementation(libs.bundles.navigation)

    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
}