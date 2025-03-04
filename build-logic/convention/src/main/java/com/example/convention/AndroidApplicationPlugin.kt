package com.example.convention

import com.android.build.api.dsl.ApplicationExtension
import com.example.convention.project.configureComposeAndroid
import com.example.convention.project.configureHiltKotlin
import com.example.convention.project.configureKotlinAndroid
import com.example.convention.project.setupViewDataBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }
            extensions.configure<ApplicationExtension> {
                setupViewDataBinding()
                configureKotlinAndroid()
                configureHiltKotlin()
                configureComposeAndroid(this)
            }
        }
    }
}
