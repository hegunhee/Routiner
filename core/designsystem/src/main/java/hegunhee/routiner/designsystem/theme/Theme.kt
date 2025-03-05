package hegunhee.routiner.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Color.Gray,
    primaryContainer = Color.Black, // colorPrimaryVariant
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryContainer = Teal200, // colorSecondaryVariant
    onSecondary = Color.Black,
    background = Color.Black, // windowBackground
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Gray,
    primaryContainer = Color.Black,
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryContainer = Teal200,
    onSecondary = Color.Black,
    background = Color.Black,
    onBackground = Color.White
)

@Composable
fun RoutinerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicDarkColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
