package test.advanced

import kotlinx.coroutines.flow.StateFlow

interface AdvancedComponent {
    val states: StateFlow<AdvancedStore.State>

    fun onAdvancedEffectClicked(indexEffect: Int)

    sealed class Output {
        data class AdvancedEffectsClicked(
            val indexEffect: Int,
        ) : Output()
    }
}
