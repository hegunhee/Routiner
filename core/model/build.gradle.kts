plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("routiner.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutine.core)
}