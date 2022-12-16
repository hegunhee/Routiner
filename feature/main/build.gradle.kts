plugins {
    id("android.feature")
}

android {

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":feature:common"))

    implementation(libs.gson)
}