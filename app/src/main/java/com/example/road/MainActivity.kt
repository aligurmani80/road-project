package com.example.road

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.road.ui.onboarding.OnboardingScreen
import com.example.road.ui.theme.RoadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContent {
            RoadTheme {
                OnboardingScreen(
                    // Both actions are navigation hooks; Phase 2 will route them.
                    onGetStarted = {},
                    onSkip = {},
                )
            }
        }
    }
}
