import com.example.convention.project.configureHiltKotlin
import com.example.convention.project.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureHiltKotlin()