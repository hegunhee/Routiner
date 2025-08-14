package routiner.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import routiner.feature.main.app.RoutinerApp
import dagger.hilt.android.AndroidEntryPoint
import routiner.core.designsystem.theme.RoutinerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoutinerTheme {
                RoutinerApp()
            }
        }
    }
}
