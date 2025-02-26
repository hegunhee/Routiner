package com.example.convention.project

import com.example.convention.setup.android
import org.gradle.api.Project

fun Project.setupViewDataBinding() {
    android {
        viewBinding {
            enable = true
        }
        dataBinding {
            enable = true
        }
    }
}