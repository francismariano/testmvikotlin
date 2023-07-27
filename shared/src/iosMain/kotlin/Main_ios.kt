import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import test.root.RootComponent
import test.root.RootComponentImpl

fun MainViewController() = ComposeUIViewController {
    App(testRoot(DefaultComponentContext(LifecycleRegistry())))
}

private fun testRoot(componentContext: ComponentContext): RootComponent =
    RootComponentImpl(
        componentContext = componentContext,
        storeFactory = LoggingStoreFactory(DefaultStoreFactory()),
    )

actual fun getPlatformName(): String = "iOS"
