import com.example.convention.project.configureHiltKotlin
import com.example.convention.setup.androidExtension
import com.example.convention.setup.libs

plugins {
    id("routiner.android.library")
}

configureHiltKotlin()

android {
    androidExtension.apply {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        buildFeatures {
            dataBinding {
                enable = true
            }
            viewBinding {
                enable = true
            }
        }
    }
}


dependencies{
    implementation(project(":core:domain"))
    implementation(project(":core:util"))
    implementation(project(":core:navigation"))
    val libs = project.extensions.libs

    implementation(libs.findLibrary("core-ktx").get())
    implementation(libs.findLibrary("appcompat").get())
    implementation(libs.findLibrary("material").get())
    implementation(libs.findLibrary("constraintlayout").get())
    implementation(libs.findLibrary("androidx-recyclerview").get())
    implementation(libs.findLibrary("junit").get())
    androidTestImplementation(libs.findLibrary("testext").get())
    androidTestImplementation(libs.findLibrary("espresso").get())

    implementation(libs.findBundle("navigation").get())

    implementation(libs.findLibrary("activity-ktx").get())
    implementation(libs.findLibrary("fragment-ktx").get())

    implementation(libs.findLibrary("lifecycle-livedata").get())
    implementation(libs.findLibrary("lifecycle-scope").get())
}