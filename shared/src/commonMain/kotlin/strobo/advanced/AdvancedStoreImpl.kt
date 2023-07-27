package test.advanced

import co.touchlab.kermit.Logger
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.launch
import test.advanced.AdvancedStore.Intent
import test.advanced.AdvancedStore.Label
import test.advanced.AdvancedStore.State
import test.bluetooth.Effect
import test.bluetooth.testRepository
import test.bluetooth.testResult

internal class AdvancedStoreImpl(
    private val storeFactory: StoreFactory,
    private val testRepository: testRepository,
) {

    private val logger = Logger.withTag("AdvancedStore")

    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): AdvancedStore =
        object :
            AdvancedStore,
            Store<Intent, State, Label> by storeFactory.create<Intent, Unit, Msg, State, Label>(
                name = "AdvancedStore",
                initialState = State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = coroutineExecutorFactory {
                    onAction<Unit> {
                        getEffects()
                    }
                    onIntent<Intent.AdvancedEffect> { intent ->
                        publish(
                            Label.GoToAdvancedEffects(
                                indexEffect = intent.indexEffect,
                            ),
                        )
                    }
                },
                reducer = { msg ->
                    when (msg) {
                        is Msg.EffectsLoaded -> {
                            copy(
                                effect1 = msg.effect1,
                                effect2 = msg.effect2,
                                effect3 = msg.effect3,
                            )
                        }
                    }
                },
            ) {}

    @OptIn(ExperimentalMviKotlinApi::class)
    private fun CoroutineExecutorScope<State, Msg, Nothing>.getEffects() {
        launch {
            val effect1 = testRepository.getEffectNormal(index = 0)
            val effect2 = testRepository.getEffectNormal(index = 1)
            val effect3 = testRepository.getEffectNormal(index = 2)
            if (effect1 is testResult.Success && effect2 is testResult.Success && effect3 is testResult.Success) {
                dispatch(
                    Msg.EffectsLoaded(
                        effect1 = effect1.value,
                        effect2 = effect2.value,
                        effect3 = effect3.value,
                    ),
                )
            }
        }
    }

    private sealed class Msg {
        data class EffectsLoaded(
            val effect1: Effect,
            val effect2: Effect,
            val effect3: Effect,
        ) : Msg()
    }
}
