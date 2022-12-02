package com.example.convention

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("android.library.convention")
                apply("android.hilt")
            }
            extensions.configure<LibraryExtension>{
                defaultConfig{
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildFeatures{
                    dataBinding = true
                    viewBinding = true
                }
            }
            dependencies{
                add("implementation",project(":domain"))
            }
        }
    }
}