package test.advanced

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun testAdvancedContent(component: AdvancedComponent) {
    val state by component.states.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
                    .background(
                        color = state.effect1.primaryColor,
                        shape = RoundedCornerShape(100.dp),
                    ),
            ) {}
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
                    .background(
                        color = state.effect2.primaryColor,
                        shape = RoundedCornerShape(100.dp),
                    ),
            ) {}
            Box(
                modifier = Modifier
                    .width(50.dp).height(50.dp)
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
                    .background(
                        color = state.effect3.primaryColor,
                        shape = RoundedCornerShape(100.dp),
                    ),
            ) {}
        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            border = BorderStroke(2.dp, color = state.effect1.primaryColor),
            onClick = { component.onAdvancedEffectClicked(indexEffect = 0) },
        ) {
            Text(
                color = Color.White,
                text = "Update color 1",
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            border = BorderStroke(2.dp, color = state.effect2.primaryColor),
            onClick = { component.onAdvancedEffectClicked(indexEffect = 1) },
        ) {
            Text(
                color = Color.White,
                text = "Update color 2",
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            border = BorderStroke(2.dp, color = state.effect3.primaryColor),
            onClick = { component.onAdvancedEffectClicked(indexEffect = 2) },
        ) {
            Text(
                color = Color.White,
                text = "Update color 3",
            )
        }
    }
}