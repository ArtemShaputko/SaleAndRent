package com.project.saleandrent.mainactvity

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import com.project.saleandrent.ui.theme.Typography
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.saleandrent.R
import com.project.saleandrent.elements.HouseInfo
import com.project.saleandrent.elements.ImageSwitcher
import com.project.saleandrent.elements.PopupBox
import com.project.saleandrent.ui.theme.SaleAndRentTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HouseMenuWrapper(
    modifier: Modifier = Modifier,
    houseInfo: HouseInfo,
    purchases: SnapshotStateList<HouseInfo>
) {
    val isAdded = remember { mutableStateOf(purchases.contains(houseInfo)) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.offset(y = 16.dp),
                containerColor = colorResource(R.color.second),
                contentColor = Color.White,
                content = {
                    if (isAdded.value) Icon(
                        Icons.Filled.Clear,
                        contentDescription = "Delete"
                    )
                    else Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                },
                onClick = {
                    if (!isAdded.value) {
                        purchases.add(houseInfo)
                    } else {
                        purchases.remove(houseInfo)
                    }
                    isAdded.value = !isAdded.value
                }
            )
        }
    ) {
        HouseMenu(modifier, houseInfo)
    }
}

@Composable
fun HouseMenu(
    modifier: Modifier = Modifier,
    houseInfo: HouseInfo
) {
    val scrollState = rememberScrollState()
    val toShowPopup = rememberSaveable {
        mutableStateOf(false)
    }
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    PopupBox(
        toShow = toShowPopup.value,
        onDismiss = { toShowPopup.value = !toShowPopup.value }
    ) {
        ImageSwitcher(
            imageItems = houseInfo.images,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            toShowPopup.value = !toShowPopup.value
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        scale = scale.coerceIn(1f, 5f)

                        offset = if (scale == 1f) Offset(0f, 0f) else offset + pan
                    }
                },
            imageModifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                ),
            contentScale = ContentScale.Fit
        )
    }
    Column(modifier = modifier.verticalScroll(scrollState)) {
        ImageSwitcher(
            imageItems = houseInfo.images,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .height(300.dp)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            toShowPopup.value = !toShowPopup.value
                        }
                    )
                }
        )
        Text(
            text = stringResource(id = houseInfo.nameId),
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterHorizontally),
            style = Typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Description:",
            modifier = Modifier.padding(horizontal = 10.dp),
            style = Typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.second).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = stringResource(id = houseInfo.descriptionId),
                modifier = Modifier.padding(7.dp),
                style = Typography.bodyMedium,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Characteristics:",
            modifier = Modifier.padding(horizontal = 10.dp),
            style = Typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .background(
                    color = Color.Gray.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = stringResource(id = houseInfo.characteristicsId),
                modifier = Modifier.padding(5.dp),
                style = Typography.bodyMedium,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "$${houseInfo.price}",
                modifier = Modifier.padding(5.dp),
                style = Typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color  = colorResource(R.color.main)
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HouseMenuPreview() {
    val purchases = rememberSaveable {
        mutableStateListOf<HouseInfo>()
    }
    SaleAndRentTheme {
        HouseMenuWrapper(houseInfo = houses[0], purchases = purchases)
    }
}