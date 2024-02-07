plugins {
    id("routiner.android.library")
}

android {
    namespace = "com.hegunhee.routiner.navigation"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.bundles.navigation)

    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)

    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.scope)
}