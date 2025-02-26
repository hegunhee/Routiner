package com.example.convention

import com.android.build.gradle.LibraryExtension
import com.example.convention.project.configureHiltKotlin
import com.example.convention.project.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid()
                configureHiltKotlin()
            }
        }
    }
}