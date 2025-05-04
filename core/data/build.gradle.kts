plugins {
    id("routiner.android.library")
}

android {
    namespace = "routiner.core.data"
    
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:util"))
    implementation(project(":core:model"))
    
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.gson)
    testImplementation(libs.assertj.core)
}