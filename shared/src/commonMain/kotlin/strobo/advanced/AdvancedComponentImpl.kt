package test.advanced

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import test.advanced.AdvancedComponent.Output
import test.advanced.AdvancedStore.Intent
import test.bluetooth.testRepository

@OptIn(ExperimentalCoroutinesApi::class)
internal class AdvancedComponentImpl(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val test: testRepository,
    private val output: (Output) -> Unit,
    private val channel: Int,
) : AdvancedComponent, ComponentContext by componentContext {

    private val scope = MainScope()

    private val store =
        instanceKeeper.getStore {
            AdvancedStoreImpl(
                storeFactory = storeFactory,
                testRepository = test,
            ).create()
        }

    init {
        lifecycle.doOnDestroy { scope.cancel() }
        store.labels.onEach { label ->
            when (label) {
                is AdvancedStore.Label.GoToAdvancedEffects -> output(
                    Output.AdvancedEffectsClicked(
                        indexEffect = label.indexEffect,
                    ),
                )
            }
        }.launchIn(scope)
    }

    @ExperimentalCoroutinesApi
    override val states: StateFlow<AdvancedStore.State> = store.stateFlow

    override fun onAdvancedEffectClicked(indexEffect: Int) {
        store.accept(Intent.AdvancedEffect(indexEffect))
    }

}
