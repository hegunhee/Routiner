plugins {
    id("android.feature")
}

android {

}

dependencies {

    implementation(project(":feature:common"))
    implementation(project(":feature:category"))
}