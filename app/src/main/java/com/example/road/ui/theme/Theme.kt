package com.example.road.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RoadAssistColorScheme = darkColorScheme(
    primary = RoadAssistOrange,
    onPrimary = PrimaryWhite,
    primaryContainer = RoadAssistOrange,
    onPrimaryContainer = PrimaryWhite,
    secondary = RoadAssistOrange,
    onSecondary = PrimaryWhite,
    background = BackgroundDark,
    onBackground = PrimaryWhite,
    surface = SurfaceDark,
    onSurface = PrimaryWhite,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = SecondaryText,
    outline = Hairline,
    outlineVariant = Hairline,
)

@Composable
fun RoadTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RoadAssistColorScheme,
        typography = Typography,
        content = content,
    )
}
