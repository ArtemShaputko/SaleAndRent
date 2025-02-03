package com.project.saleandrent.elements

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.saleandrent.R
import com.project.saleandrent.ui.theme.SaleAndRentTheme
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageItem(val resourceId: Int) : Parcelable

@Composable
fun ImageSwitcher(
    imageItems: List<ImageItem>,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val pagerState = rememberPagerState { imageItems.size }
    val curIndex = remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState
        ) { index ->
            Image(
                painter = painterResource(id = imageItems[index].resourceId),
                contentDescription = null,
                modifier = imageModifier.fillMaxSize(),
                contentScale = contentScale
            )
        }
        IconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage - 1
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(10.dp)
                .size(50.dp)
                .background(
                    color = colorResource(id = R.color.main).copy(alpha = 0.5f),
                    shape = CircleShape
                )
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "back",
                modifier = Modifier.size(50.dp)
            )
        }
        IconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(10.dp)
                .size(50.dp)
                .background(
                    color = colorResource(id = R.color.main).copy(alpha = 0.5f),
                    shape = CircleShape
                )
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "forward",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSwitcherPreview() {
    SaleAndRentTheme {
        ImageSwitcher(
            imageItems = listOf(
                ImageItem(R.drawable.house1),
                ImageItem(R.drawable.house1_1),
                ImageItem(R.drawable.house1_2),
                ImageItem(R.drawable.house1_3),
                ImageItem(R.drawable.house1_4),
            )
        )
    }
}
