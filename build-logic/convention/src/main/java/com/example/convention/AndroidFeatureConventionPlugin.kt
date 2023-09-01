package com.example.convention

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

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
                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                    debug {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies{

                add("implementation",libs.findLibrary("core-ktx").get())
                add("implementation",libs.findLibrary("appcompat").get())
                add("implementation",libs.findLibrary("material").get())
                add("implementation",libs.findLibrary("constraintlayout").get())
                add("implementation",libs.findLibrary("junit").get())
                add("androidTestImplementation",libs.findLibrary("testext").get())
                add("androidTestImplementation",libs.findLibrary("espresso").get())

                add("implementation",libs.findBundle("navigation").get())

                add("implementation",libs.findLibrary("activity-ktx").get())
                add("implementation",libs.findLibrary("fragment-ktx").get())

                add("implementation",libs.findLibrary("lifecycle-livedata").get())
                add("implementation",libs.findLibrary("lifecycle-scope").get())
            }

        }
    }
}