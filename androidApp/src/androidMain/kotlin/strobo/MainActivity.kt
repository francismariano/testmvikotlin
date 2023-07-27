package test

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import test.root.RootComponent
import test.root.RootComponentImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = testRoot(defaultComponentContext())
        setContent {
            MainView(root)
        }
    }

    private fun testRoot(componentContext: ComponentContext): RootComponent =
        RootComponentImpl(
            componentContext = componentContext,
            storeFactory = LoggingStoreFactory(DefaultStoreFactory()),
        )
}
