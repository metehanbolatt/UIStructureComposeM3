package com.metehanbolat.uistructurecomposem3.components.derived

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.metehanbolat.uistructurecomposem3.R
import kotlin.math.max

@Composable
fun CalculatedBoxes(
    images: List<Int>,
    imageSize: Dp = 40.dp,
    spaceBetween: Dp = 10.dp
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(all = 14.dp)
    ) {

        val numberOfVisibleBox = remember {
            derivedStateOf {
                max(
                    a = 0,
                    b = this.maxWidth.div(spaceBetween + imageSize).toInt().minus(1)
                )
            }
        }

        val remainingBox = remember {
            derivedStateOf {
                images.size - numberOfVisibleBox.value
            }
        }

        Row {
            images.take(numberOfVisibleBox.value).forEach {
                Image(
                    modifier = Modifier.size(imageSize),
                    painter = painterResource(id = it),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(spaceBetween))
            }

            if (remainingBox.value > 0) {
                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = remainingBox.value.toString())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatedBoxesPreview() {
    val images = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CalculatedBoxes(images = images)
    }
}