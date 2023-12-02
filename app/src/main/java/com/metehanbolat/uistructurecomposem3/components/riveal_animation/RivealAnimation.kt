package com.metehanbolat.uistructurecomposem3.components.riveal_animation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

@ExperimentalFoundationApi
fun PagerState.offSetForPage(page: Int) = currentPage - page + currentPageOffsetFraction

@ExperimentalFoundationApi
fun PagerState.endOffsetForPage(page: Int) = offSetForPage(page = page).coerceAtMost(0f)

@ExperimentalFoundationApi
fun PagerState.startOffsetForPage(page: Int) = offSetForPage(page = page).coerceAtLeast(0f)

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RivealAnimation() {
    val state = rememberPagerState()

    val (offSetY, setOffsetY) = remember {
        mutableStateOf(0f)
    }

    HorizontalPager(
        state = state,
        pageCount = locations.count(),
        modifier = Modifier
            .pointerInteropFilter {
                setOffsetY(it.y)
                false
            }
            .padding(16.dp)
            .clip(RoundedCornerShape(14.dp))
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    val pageOffset = state.offSetForPage(page = page)
                    translationX = size.width * pageOffset

                    val endOffset = state.endOffsetForPage(page = page)
                    shadowElevation = 20f
                    shape = CirclePath(
                        progress = 1f - endOffset.absoluteValue,
                        origin = Offset(
                            size.width,
                            offSetY
                        )
                    )
                    clip = true

                    val absoluteOffset = state.offSetForPage(page = page).absoluteValue
                    val scale = 1f + absoluteOffset.absoluteValue * .3f

                    scaleX = scale
                    scaleY = scale

                    val startOffset = state.startOffsetForPage(page = page)
                    alpha = (2f - startOffset) / 2
                }
        ) {
            val location = locations[page]
            Image(
                painter = painterResource(
                    id = location.image
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(.5f)
                    .background(Brush.verticalGradient(listOf(Color.Unspecified, Color.Unspecified)))
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(18.dp)
            ) {
                Text(
                    text = location.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = location.description, fontSize = 16.sp, color = Color(0xFFCCCCCC))
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun RivealAnimationPreview() {
    RivealAnimation()
}