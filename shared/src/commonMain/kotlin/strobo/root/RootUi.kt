package test.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import test.advanced.testAdvancedContent
import test.advancedEffects.testAdvancedEffectsContent

@Composable
internal fun testRootContent(component: RootComponent) {
    Children(
        stack = component.childStack,
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Advanced -> testAdvancedContent(child.component)
            is RootComponent.Child.AdvancedEffects -> testAdvancedEffectsContent(child.component)
        }
    }
}