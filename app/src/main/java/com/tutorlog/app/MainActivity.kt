package com.tutorlog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.tutorlog.app.ui.theme.TutorLogTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            TutorLogTheme {
                SideEffect {
                    window.statusBarColor = Color.Transparent.toArgb()
                    window.navigationBarColor = Color.Transparent.toArgb()
                    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
                    windowInsetsController.isAppearanceLightStatusBars = false // false = white content
                    windowInsetsController.isAppearanceLightNavigationBars = false // false = white nav bar content
                }
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}