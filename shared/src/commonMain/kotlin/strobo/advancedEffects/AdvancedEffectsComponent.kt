package test.advancedEffects

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.StateFlow

interface AdvancedEffectsComponent {
    val states: StateFlow<AdvancedEffectsStore.State>

    fun onColorChanged(num: Int, color: Color)

    sealed class Output
}
