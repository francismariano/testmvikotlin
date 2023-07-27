package test.advancedEffects

import androidx.compose.ui.graphics.Color
import co.touchlab.kermit.Logger
import com.arkivanov.mvikotlin.core.store.Store

interface AdvancedEffectsStore : Store<AdvancedEffectsStore.Intent, AdvancedEffectsStore.State, AdvancedEffectsStore.Label> {
    sealed class Intent {
        data class ColorSelected(val num: Int, val color: Color) : Intent()
    }

    data class State (
        val primaryColor: Color = Color.Red,
    )

    sealed class Label
}
