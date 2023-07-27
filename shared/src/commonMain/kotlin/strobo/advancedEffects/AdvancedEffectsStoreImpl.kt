package test.advancedEffects

import co.touchlab.kermit.Logger
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import test.advancedEffects.AdvancedEffectsStore.Intent
import test.advancedEffects.AdvancedEffectsStore.Label
import test.advancedEffects.AdvancedEffectsStore.State
import test.bluetooth.Effect
import test.bluetooth.testRepository
import test.bluetooth.testResult

internal class AdvancedEffectsStoreImpl(
    private val storeFactory: StoreFactory,
    private val testRepository: testRepository,
    private val indexEffect: Int,
) {

    private var colorSharedFlow = MutableSharedFlow<() -> Unit>()
    private val logger = Logger.withTag("AdvancedEffectsStoreImpl")

    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): AdvancedEffectsStore =
        object :
            AdvancedEffectsStore,
            Store<Intent, State, Label> by storeFactory.create<Intent, Unit, Msg, State, Label>(
                name = "AdvancedEffectsStore",
                initialState = State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = coroutineExecutorFactory {
                    onAction<Unit> {
                        logger.d { "*** Start onAction ***" }
                        getEffect()
                        monitorColor()
                    }
                    onIntent<Intent.ColorSelected> { intent ->
                        updateColor(
                            state.getEffectFromState().copy(
                                primaryColor = if (intent.num == 1) intent.color else state.primaryColor,
                            ),
                        )
                    }
                },
                reducer = { msg ->
                    when (msg) {
                        is Msg.EffectLoaded -> copy(
                            primaryColor = msg.effect.primaryColor,
                        )
                    }
                },
            ) {}

    @OptIn(ExperimentalMviKotlinApi::class)
    private fun CoroutineExecutorScope<State, Msg, Nothing>.getEffect() {
        launch {
            when (val effect =
                testRepository.getEffectNormal(index = indexEffect)) {
                is testResult.Success -> {
                    dispatch(Msg.EffectLoaded(effect.value))
                }
            }
        }
    }

    @OptIn(ExperimentalMviKotlinApi::class)
    private fun CoroutineExecutorScope<State, Msg, Nothing>.updateColor(effect: Effect) {
        launch {
            colorSharedFlow.emit { updateEffectNormal(effect) }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalMviKotlinApi::class)
    private fun CoroutineExecutorScope<State, Msg, Nothing>.monitorColor() {
        launch {
            colorSharedFlow.sample(300).collect {
                it.invoke()
            }
        }
    }

    @OptIn(ExperimentalMviKotlinApi::class)
    private fun CoroutineExecutorScope<State, Msg, Nothing>.updateEffectNormal(effect: Effect) {
        launch {
            when (val result = testRepository.updateEffectNormal(effect = effect, index = indexEffect)) {
                is testResult.Success -> dispatch(Msg.EffectLoaded(effect = result.value))
            }
        }
    }

    private fun State.getEffectFromState(): Effect {
        return Effect(
            primaryColor = primaryColor,
        )
    }

    private sealed class Msg {
        data class EffectLoaded(val effect: Effect) : Msg()
    }
}
