plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies{
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}
gradlePlugin{
    plugins{
        register("androidLibrary"){
            id = "android.library.convention"
            implementationClass = "com.example.convention.AndroidLibraryConventionPlugin"
        }
        register("androidHilt"){
            id = "android.hilt"
            implementationClass = "com.example.convention.AndroidHiltConventionPlugin"
        }
    }
}