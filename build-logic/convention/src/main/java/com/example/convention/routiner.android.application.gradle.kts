import com.example.convention.project.configureHiltKotlin
import com.example.convention.project.configureKotlinAndroid
import com.example.convention.setup.androidExtension

plugins {
    id("com.android.application")
}

android {
    androidExtension.apply {
        buildFeatures {
            dataBinding {
                enable = true
            }
            viewBinding {
                enable = true
            }
        }
    }
}

configureKotlinAndroid()
configureHiltKotlin()
