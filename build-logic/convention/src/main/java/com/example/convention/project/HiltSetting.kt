package com.example.convention.project

import com.example.convention.setup.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltKotlin() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.kapt")
    }

    dependencies {
        "kapt"(libs.findLibrary("hilt.compiler").get())
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        dependencies {
            "implementation"(libs.findLibrary("hilt.core").get())
        }
    }

    pluginManager.withPlugin("com.android.base") {
        pluginManager.apply("dagger.hilt.android.plugin")
        dependencies {
            "implementation"(libs.findLibrary("hilt.android").get())
        }
    }
}

class HiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHiltKotlin()
        }
    }
}
