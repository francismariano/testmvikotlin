package test.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val onSurface: Color,
    val primary: Color,
) {
    object Nigth : ThemeColors(
        onSurface = Color(0xFF000000),
        primary = Color(0xFF6F0000),
    )

    object Day : ThemeColors(
        onSurface = Color(0xFF000000),
        primary = Color(0xFF6F0000),
    )
}
