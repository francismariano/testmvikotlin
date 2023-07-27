package test.advanced

import androidx.compose.ui.graphics.Color
import com.arkivanov.mvikotlin.core.store.Store
import test.bluetooth.Effect

interface AdvancedStore : Store<AdvancedStore.Intent, AdvancedStore.State, AdvancedStore.Label> {
    sealed class Intent {
        data class AdvancedEffect(val indexEffect: Int) : Intent()
    }

    data class State(
        val effect1: Effect = Effect(
            primaryColor = Color.Red,
        ),
        val effect2: Effect = Effect(
            primaryColor = Color.Red,
        ),
        val effect3: Effect = Effect(
            primaryColor = Color.Red,
        ),
    )

    sealed class Label {
        data class GoToAdvancedEffects(val indexEffect: Int) : Label()
    }
}
