package test.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ThemeColors.Nigth.primary,
    onSurface = ThemeColors.Nigth.onSurface,
)

private val LightColorPalette = lightColors(
    primary = ThemeColors.Day.primary,
    onSurface = ThemeColors.Day.onSurface,

)

@Composable
internal fun testAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}
