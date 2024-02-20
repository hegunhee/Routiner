plugins {
    id("routiner.android.feature")
}

android {
    namespace = "com.hegunhee.routiner.insertRoutine"
}

dependencies {

    implementation(project(":feature:common"))
    implementation(project(":feature:category"))
    implementation(project(":feature:main"))
}