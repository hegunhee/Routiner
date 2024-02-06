@file:Suppress("DSL_SCOPE_VIOLATION")
buildscript {
    dependencies {
        classpath(libs.hilt.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

task("clean",Delete::class) {
    delete = setOf(rootProject.buildDir)
}