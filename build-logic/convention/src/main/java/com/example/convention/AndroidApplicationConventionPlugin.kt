package com.example.convention

import com.android.build.api.dsl.ApplicationExtension
import com.example.convention.project.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("android.hilt")
            }
            extensions.configure<ApplicationExtension>{
                configureKotlinAndroid()
                defaultConfig{
                    targetSdk = 33
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildFeatures{
                    dataBinding = true
                    viewBinding = true
                }
            }
        }
    }
}