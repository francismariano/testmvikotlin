import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import test.root.RootComponent
import test.root.testRootContent
import test.theme.testAppTheme

@Composable
internal fun App(component: RootComponent) {
    testAppTheme {
        if (getPlatformName() == "Android") {
            Surface(color = Color.Transparent) {
                testRootContent(component)
            }
        } else {
            Surface(color = Color.Black) {
                testRootContent(component)
            }
        }
    }
}
expect fun getPlatformName(): String
