plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.hegunhee.daily"
}

dependencies {

    implementation(project(":feature:common"))
    implementation(project(":feature:category"))
}