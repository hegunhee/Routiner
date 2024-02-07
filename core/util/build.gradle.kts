plugins {
    id("routiner.android.library")
}

android {
    namespace = "com.hegunhee.routiner.util"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.testext)
    androidTestImplementation(libs.espresso)
}