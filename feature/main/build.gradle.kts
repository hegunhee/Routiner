plugins {
    id("android.feature")
}

android {

}

dependencies {

    implementation(project(":feature:common"))

    implementation(libs.gson)
}