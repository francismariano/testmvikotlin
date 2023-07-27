package test.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import test.advanced.AdvancedComponent
import test.advancedEffects.AdvancedEffectsComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class Advanced(val component: AdvancedComponent) : Child()
        data class AdvancedEffects(val component: AdvancedEffectsComponent) : Child()
    }
}
