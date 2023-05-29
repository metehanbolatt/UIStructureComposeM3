package com.metehanbolat.uistructurecomposem3.components.color_filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.metehanbolat.uistructurecomposem3.R

@Composable
fun CustomColorFilter() {
    var blurChecked by remember { mutableStateOf(false) }
    val matrix by remember { mutableStateOf(ColorMatrix()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .clip(RoundedCornerShape(20.dp))
                .blur(if (blurChecked) 5.dp else 0.dp),
            painter = painterResource(id = R.drawable.view),
            colorFilter = ColorFilter.colorMatrix(colorMatrix = matrix),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Switch(
            checked = blurChecked,
            onCheckedChange = {
                blurChecked = it
                if (it) matrix.setToSaturation(0f) else matrix.setToSaturation(1f)
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CustomColorFilterPreview() {
    CustomColorFilter()
}