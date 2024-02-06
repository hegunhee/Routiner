plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
            id = "android.routiner.hilt"
            implementationClass = "com.example.convention.project.HiltKotlinPlugin"
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