package com.example.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.main.app.RoutinerApp
import dagger.hilt.android.AndroidEntryPoint
import hegunhee.routiner.designsystem.theme.RoutinerTheme

@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoutinerTheme {
                RoutinerApp()
            }
        }
    }
}
