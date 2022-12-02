package com.example.convention

import com.android.build.api.dsl.ApplicationExtension
import com.example.convention.project.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("android.hilt")
            }
            extensions.configure<ApplicationExtension>{
                configureKotlinAndroid(this)
                defaultConfig{
                    targetSdk = 32
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildFeatures{
                    dataBinding = true
                    viewBinding = true
                }
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies{
                add("implementation",project(":domain"))
                add("implementation",project(":data"))
                add("implementation",project(":feature"))


                add("implementation",libs.findBundle("navigation").get())

                add("implementation",libs.findLibrary("activity-ktx").get())
                add("implementation",libs.findLibrary("fragment-ktx").get())

                add("implementation",libs.findLibrary("lifecycle-livedata").get())
                add("implementation",libs.findLibrary("lifecycle-scope").get())

                add("implementation",libs.findBundle("room").get())
                add("kapt",libs.findLibrary("room-compiler").get())

                add("implementation",libs.findLibrary("gson").get())
            }
        }
    }
}