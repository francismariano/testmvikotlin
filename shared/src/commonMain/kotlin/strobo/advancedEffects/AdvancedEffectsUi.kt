package test.advancedEffects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun testAdvancedEffectsContent(component: AdvancedEffectsComponent) {
    val state by component.states.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier = Modifier
                .width(50.dp).height(50.dp)
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
                .background(color = state.primaryColor, shape = RoundedCornerShape(100.dp)),
        ) {}
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White)
                    .background(color = Color.Red)
                    .clickable { component.onColorChanged(1, Color.Red) },
            ) {}
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White)
                    .background(color = Color.Green)
                    .clickable { component.onColorChanged(1, Color.Green) },
            ) {}
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White)
                    .background(color = Color.Blue)
                    .clickable { component.onColorChanged(1, Color.Blue) },
            ) {}
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White)
                    .background(color = Color.Yellow)
                    .clickable { component.onColorChanged(1, Color.Yellow) },
            ) {}
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White)
                    .background(color = Color.Magenta)
                    .clickable { component.onColorChanged(1, Color.Magenta) },
            ) {}
        }
    }
}
