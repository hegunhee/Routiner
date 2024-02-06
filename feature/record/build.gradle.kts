plugins {
    id("android.feature")
}

android {
    namespace = "com.hegunhee.record"
}

dependencies {

    implementation(project(":feature:common"))
}