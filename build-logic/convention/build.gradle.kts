plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
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
        register("androidApplication"){
            id = "android.application"
            implementationClass = "com.example.convention.AndroidApplicationConventionPlugin"
        }
        register("androidFeature"){
            id = "android.feature"
            implementationClass = "com.example.convention.AndroidFeatureConventionPlugin"
        }
    }
}