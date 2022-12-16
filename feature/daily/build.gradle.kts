plugins {
    id("android.feature")
}

android {

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":feature:common"))
    implementation(project(":feature:main"))
    implementation(project( ":feature:category"))
}