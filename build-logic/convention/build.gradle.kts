plugins {
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.hilt.plugin)
}
gradlePlugin {
    plugins {
        register("kotlinHilt") {
            id = "routiner.hilt"
            implementationClass = "com.example.convention.project.HiltPlugin"
        }
        register("androidApplication") {
            id = "routiner.android.application"
            implementationClass = "com.example.convention.AndroidApplicationPlugin"
        }
        register("androidFeature") {
            id = "routiner.android.feature"
            implementationClass = "com.example.convention.AndroidFeaturePlugin"
        }
        register("androidLibrary") {
            id = "routiner.android.library"
            implementationClass = "com.example.convention.AndroidLibraryPlugin"
        }
        register("androidCompose") {
            id = "routiner.android.compose"
            implementationClass = "com.example.convention.AndroidComposePlugin"
        }
    }
}
