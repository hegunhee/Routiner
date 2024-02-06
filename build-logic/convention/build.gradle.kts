plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.hilt.plugin)
}
gradlePlugin{
    plugins{
        register("androidLibrary"){
            id = "android.library.convention"
            implementationClass = "com.example.convention.AndroidLibraryConventionPlugin"
        }
        register("androidHilt"){
            id = "routiner.android.hilt"
            implementationClass = "com.example.convention.project.HiltKotlinPlugin"
        }
        register("androidApplication"){
            id = "android.application"
            implementationClass = "com.example.convention.AndroidApplicationConventionPlugin"
        }
    }
}