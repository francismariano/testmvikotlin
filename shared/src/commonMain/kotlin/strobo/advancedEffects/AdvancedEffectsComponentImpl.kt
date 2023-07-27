package test.advancedEffects

import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import test.advancedEffects.AdvancedEffectsStore.Intent
import test.bluetooth.testRepository

internal class AdvancedEffectsComponentImpl(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val test: testRepository,
    private val indexEffect: Int,
) : AdvancedEffectsComponent, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            AdvancedEffectsStoreImpl(
                storeFactory = storeFactory,
                testRepository = test,
                indexEffect = indexEffect,
            ).create()
        }

    @ExperimentalCoroutinesApi
    override val states: StateFlow<AdvancedEffectsStore.State> = store.stateFlow

    override fun onColorChanged(num: Int, color: Color) {
        store.accept(Intent.ColorSelected(num, color))
    }
}
