plugins {
    id("android.feature")
}

android {
    namespace = "com.hegunhee.setting"
}

dependencies {

    implementation(project(":feature:common"))
}