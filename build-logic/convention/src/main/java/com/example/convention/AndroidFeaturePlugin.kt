package com.example.convention

import com.android.build.gradle.LibraryExtension
import com.example.convention.project.configureHiltKotlin
import com.example.convention.setup.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("routiner.android.library")
            }
            configureHiltKotlin()

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    dataBinding {
                        enable = true
                    }
                   viewBinding {
                       enable = true
                   }
                }
            }

            dependencies {
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:util"))
                add("implementation", project(":core:navigation"))

                add("implementation", libs.findLibrary("core-ktx").get())
                add("implementation", libs.findLibrary("appcompat").get())
                add("implementation", libs.findLibrary("material").get())
                add("implementation", libs.findLibrary("constraintlayout").get())
                add("implementation", libs.findLibrary("androidx-recyclerview").get())
                add("implementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("ext-junit").get())
                add("androidTestImplementation", libs.findLibrary("espresso-core").get())

                add("implementation", libs.findBundle("navigation").get())

                add("implementation", libs.findLibrary("activity-ktx").get())
                add("implementation", libs.findLibrary("fragment-ktx").get())

                add("implementation", libs.findLibrary("lifecycle-livedata").get())
                add("implementation", libs.findLibrary("lifecycle-scope").get())
            }

        }
    }
}