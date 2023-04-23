package com.metehanbolat.uistructurecomposem3.components.linear_indicator

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun CustomLinearProgressIndicator() {

    var floatValue by remember { mutableStateOf(0f) }
    val floatState by animateFloatAsState(
        targetValue = floatValue,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 0,
            easing = LinearEasing
        ),
        label = "Linear Indicator"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = floatState
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (floatValue == 1f) {
                    floatValue = 0f
                } else {
                    floatValue += 0.2f
                }
            }
        ) {
            Text(text = "Button")
        }
    }
}