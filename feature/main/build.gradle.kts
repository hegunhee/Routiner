plugins {
    id("android.feature")
}

android {
    namespace = "com.example.main"

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":common"))

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

    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.scope)

    implementation(libs.gson)
}