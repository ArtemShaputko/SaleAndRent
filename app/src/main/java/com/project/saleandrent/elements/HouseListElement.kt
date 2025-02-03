package com.project.saleandrent.elements

import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.saleandrent.R
import com.project.saleandrent.ui.theme.SaleAndRentTheme
import com.project.saleandrent.ui.theme.Typography
import kotlinx.parcelize.Parcelize

@Parcelize
data class HouseInfo(
    val images: List<ImageItem>,
    val nameId: Int,
    val descriptionId: Int,
    val shortDescriptionId: Int,
    val characteristicsId: Int,
    val price: Float,
) : Parcelable

@Composable
fun <T: Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)

@Composable
fun HouseListElement(
    name: String,
    description: String,
    price: Float,
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(2.dp, colorResource(id = R.color.second)),
        modifier = modifier
            .clickable(onClick = onClick)
            .size(150.dp),

        ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painter,
                contentDescription = "House",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(130.dp)
                    .aspectRatio(1f)
                    .padding(start = 10.dp, top = 10.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    top = 20.dp,
                    end = 10.dp,
                    bottom = 10.dp
                )
            ) {
                Text(text = name, style = Typography.bodyLarge)
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = description,
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "$$price", style = Typography.bodySmall, fontWeight = FontWeight.Thin)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementPreview() {
    SaleAndRentTheme {
        HouseListElement("Some cool house",
            "Really attractive and interesting house",
            1111.0f,
            painterResource(id = R.drawable.house1),
            {})
    }
}