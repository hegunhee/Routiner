plugins {
    id("android.feature")
}

android {

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":feature:main"))
}