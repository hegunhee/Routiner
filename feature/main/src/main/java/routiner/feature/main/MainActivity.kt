package routiner.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import routiner.feature.main.app.RoutinerApp
import dagger.hilt.android.AndroidEntryPoint
import routiner.core.designsystem.theme.RoutinerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoutinerTheme {
                RoutinerApp()
            }
        }
    }
}
