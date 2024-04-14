plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.hegunhee.setting"
}

dependencies {

    implementation(project(":feature:common"))
    implementation(project(":feature:main"))
}