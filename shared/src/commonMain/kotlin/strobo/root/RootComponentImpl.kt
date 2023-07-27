package test.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import test.advanced.AdvancedComponent
import test.advanced.AdvancedComponentImpl
import test.advancedEffects.AdvancedEffectsComponent
import test.advancedEffects.AdvancedEffectsComponentImpl
import test.bluetooth.testRepository
import test.root.RootComponent.Child

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val advancedComponent: (ComponentContext, (AdvancedComponent.Output) -> Unit, channel: Int) -> AdvancedComponent,
    private val advancedEffectsComponent: (ComponentContext, indexEffect: Int, ) -> AdvancedEffectsComponent,
) : RootComponent, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        advancedComponent = { childContext, output, channel ->
            AdvancedComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                test = testRepository,
                output = output,
                channel = channel,
            )
        },
        advancedEffectsComponent = { childContext,  indexEffect ->
            AdvancedEffectsComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                test = testRepository,
                indexEffect = indexEffect,
            )
        },
    )

    private val navigation = StackNavigation<Configuration>()

    private val stack =
        childStack(
            source = navigation,
            initialConfiguration = Configuration.Advanced(channel = 1),
            handleBackButton = true,
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child =
        when (configuration) {

            is Configuration.Advanced -> Child.Advanced(
                advancedComponent(
                    componentContext,
                    ::onAdvancedOutput,
                    configuration.channel,
                ),
            )

            is Configuration.AdvancedEffects -> Child.AdvancedEffects(
                advancedEffectsComponent(
                    componentContext,
                    configuration.indexEffect,
                ),
            )
        }

    private fun onAdvancedOutput(output: AdvancedComponent.Output): Unit =
        when (output) {

            is AdvancedComponent.Output.AdvancedEffectsClicked -> navigation.push(
                Configuration.AdvancedEffects(
                    indexEffect = output.indexEffect,
                ),
            )
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        data class Advanced(val channel: Int) : Configuration()

        @Parcelize
        data class AdvancedEffects(
            val indexEffect: Int,
        ) : Configuration()
    }
}
