package com.example.convention

import com.android.build.gradle.LibraryExtension
import com.example.convention.project.configureComposeAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureComposeAndroid(this)
            }
        }
    }
}