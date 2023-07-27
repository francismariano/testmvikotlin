import androidx.compose.runtime.Composable
import test.root.RootComponent

@Composable fun MainView(component: RootComponent) = App(component)

actual fun getPlatformName(): String = "Android"