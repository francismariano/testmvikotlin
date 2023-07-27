package test.bluetooth

import androidx.compose.ui.graphics.Color
import co.touchlab.kermit.Logger
import kotlin.native.concurrent.ThreadLocal

sealed class testResult<out T> {
    data class Success<T>(val value: T) : testResult<T>()
}

data class Effect(
    var primaryColor: Color,
)

@ThreadLocal
object testRepository {
    private val logger = Logger.withTag("testRepository")
    private val fakeEffects = mutableListOf<Effect>()

    init {
        fakeEffects.add(Effect(primaryColor = Color.Red))
        fakeEffects.add(Effect(primaryColor = Color.Green))
        fakeEffects.add(Effect(primaryColor = Color.Blue))
    }

    fun getEffectNormal(index: Int): testResult<Effect> {
        return getEffect(index)
    }

    private fun getEffect(index: Int): testResult<Effect> {
        return testResult.Success(
            fakeEffects[index],
        )
    }

    fun updateEffectNormal(
        effect: Effect,
        index: Int,
    ): testResult<Effect> {
        return updateEffect(index = index, effect =  effect)
    }

    private fun updateEffect(
        index: Int,
        effect: Effect,
    ): testResult<Effect> {
        fakeEffects[index].primaryColor = effect.primaryColor
        return testResult.Success(fakeEffects[index])
    }
}