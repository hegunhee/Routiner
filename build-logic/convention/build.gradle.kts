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
        register("kotlinHilt"){
            id = "routiner.hilt"
            implementationClass = "com.example.convention.project.HiltPlugin"
        }
    }
}