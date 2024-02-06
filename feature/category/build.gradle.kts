plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.hegunhee.category"
}

dependencies {

    implementation(project(":feature:common"))
}