package com.project.saleandrent.mainactvity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.saleandrent.R
import com.project.saleandrent.elements.HouseInfo
import com.project.saleandrent.elements.HouseListElement
import com.project.saleandrent.elements.ImageItem
import com.project.saleandrent.elements.MainNavRoutes
import com.project.saleandrent.ui.theme.SaleAndRentTheme

val houses = listOf(
    HouseInfo(
        nameId = R.string.house1_name,
        descriptionId = R.string.house1_description,
        shortDescriptionId = R.string.house1_short_description,
        characteristicsId = R.string.house1_characteristics,
        price = 250000.0f,
        images = listOf(
            ImageItem(R.drawable.house2),
            ImageItem(R.drawable.house2_1),
            ImageItem(R.drawable.house2_2),
            ImageItem(R.drawable.house2_3),
            ImageItem(R.drawable.house2_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house2_name,
        descriptionId = R.string.house2_description,
        shortDescriptionId = R.string.house2_short_description,
        characteristicsId = R.string.house2_characteristics,
        price = 300000.0f,
        images = listOf(
            ImageItem(R.drawable.house3),
            ImageItem(R.drawable.house3_1),
            ImageItem(R.drawable.house3_2),
            ImageItem(R.drawable.house3_3),
            ImageItem(R.drawable.house3_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house3_name,
        descriptionId = R.string.house3_description,
        shortDescriptionId = R.string.house3_short_description,
        characteristicsId = R.string.house3_characteristics,
        price = 175000.0f,
        images = listOf(
            ImageItem(R.drawable.house4),
            ImageItem(R.drawable.house4_1),
            ImageItem(R.drawable.house4_2),
            ImageItem(R.drawable.house4_3),
            ImageItem(R.drawable.house4_4),
            ImageItem(R.drawable.house4_5)
        )
    ),
    HouseInfo(
        nameId = R.string.house4_name,
        descriptionId = R.string.house4_description,
        shortDescriptionId = R.string.house4_short_description,
        characteristicsId = R.string.house4_characteristics,
        price = 450000.0f,
        images = listOf(
            ImageItem(R.drawable.house5),
            ImageItem(R.drawable.house5_1),
            ImageItem(R.drawable.house5_2),
            ImageItem(R.drawable.house5_3)
        )
    ),
    HouseInfo(
        nameId = R.string.house5_name,
        descriptionId = R.string.house5_description,
        shortDescriptionId = R.string.house5_short_description,
        characteristicsId = R.string.house5_characteristics,
        price = 150000.0f,
        images = listOf(
            ImageItem(R.drawable.house6),
            ImageItem(R.drawable.house6_1),
            ImageItem(R.drawable.house6_2),
            ImageItem(R.drawable.house6_3),
            ImageItem(R.drawable.house6_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house6_name,
        descriptionId = R.string.house6_description,
        shortDescriptionId = R.string.house6_short_description,
        characteristicsId = R.string.house6_characteristics,
        price = 500000.0f,
        images = listOf(
            ImageItem(R.drawable.house7),
            ImageItem(R.drawable.house7_1),
            ImageItem(R.drawable.house7_2),
            ImageItem(R.drawable.house7_3),
            ImageItem(R.drawable.house7_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house7_name,
        descriptionId = R.string.house7_description,
        shortDescriptionId = R.string.house7_short_description,
        characteristicsId = R.string.house7_characteristics,
        price = 275000.0f,
        images = listOf(
            ImageItem(R.drawable.house8),
            ImageItem(R.drawable.house8_1),
            ImageItem(R.drawable.house8_2),
            ImageItem(R.drawable.house8_3),
            ImageItem(R.drawable.house8_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house8_name,
        descriptionId = R.string.house8_description,
        shortDescriptionId = R.string.house8_short_description,
        characteristicsId = R.string.house8_characteristics,
        price = 320000.0f,
        images = listOf(
            ImageItem(R.drawable.house9),
            ImageItem(R.drawable.house9_1),
            ImageItem(R.drawable.house9_2),
            ImageItem(R.drawable.house9_3),
            ImageItem(R.drawable.house9_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house9_name,
        descriptionId = R.string.house9_description,
        shortDescriptionId = R.string.house9_short_description,
        characteristicsId = R.string.house9_characteristics,
        price = 225000.0f,
        images = listOf(
            ImageItem(R.drawable.house1),
            ImageItem(R.drawable.house1_1),
            ImageItem(R.drawable.house1_2),
            ImageItem(R.drawable.house1_3),
            ImageItem(R.drawable.house1_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house10_name,
        descriptionId = R.string.house10_description,
        shortDescriptionId = R.string.house10_short_description,
        characteristicsId = R.string.house10_characteristics,
        price = 180000.0f,
        images = listOf(
            ImageItem(R.drawable.house10),
            ImageItem(R.drawable.house10_1),
            ImageItem(R.drawable.house10_2),
            ImageItem(R.drawable.house10_3),
            ImageItem(R.drawable.house10_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house11_name,
        descriptionId = R.string.house11_description,
        shortDescriptionId = R.string.house11_short_description,
        characteristicsId = R.string.house11_characteristics,
        price = 370000.0f,
        images = listOf(
            ImageItem(R.drawable.house11),
            ImageItem(R.drawable.house11_1),
            ImageItem(R.drawable.house11_2),
            ImageItem(R.drawable.house11_3),
            ImageItem(R.drawable.house11_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house12_name,
        descriptionId = R.string.house12_description,
        shortDescriptionId = R.string.house12_short_description,
        characteristicsId = R.string.house12_characteristics,
        price = 290000.0f,
        images = listOf(
            ImageItem(R.drawable.house12),
            ImageItem(R.drawable.house12_1),
            ImageItem(R.drawable.house12_2),
            ImageItem(R.drawable.house12_3)
        )
    ),
    HouseInfo(
        nameId = R.string.house13_name,
        descriptionId = R.string.house13_description,
        shortDescriptionId = R.string.house13_short_description,
        characteristicsId = R.string.house13_characteristics,
        price = 210000.0f,
        images = listOf(
            ImageItem(R.drawable.house13),
            ImageItem(R.drawable.house13_1),
            ImageItem(R.drawable.house13_2),
            ImageItem(R.drawable.house13_3)
        )
    ),
    HouseInfo(
        nameId = R.string.house14_name,
        descriptionId = R.string.house14_description,
        shortDescriptionId = R.string.house14_short_description,
        characteristicsId = R.string.house14_characteristics,
        price = 340000.0f,
        images = listOf(
            ImageItem(R.drawable.house14),
            ImageItem(R.drawable.house14_1),
            ImageItem(R.drawable.house14_2),
            ImageItem(R.drawable.house14_3),
            ImageItem(R.drawable.house14_4)
        )
    ),
    HouseInfo(
        nameId = R.string.house15_name,
        descriptionId = R.string.house15_description,
        shortDescriptionId = R.string.house15_short_description,
        characteristicsId = R.string.house15_characteristics,
        price = 260000.0f,
        images = listOf(
            ImageItem(R.drawable.house15),
            ImageItem(R.drawable.house15_1),
            ImageItem(R.drawable.house15_2),
            ImageItem(R.drawable.house15_3)
        )
    )
)

@Composable
fun ListMenu(
    elements: List<HouseInfo>,
    navController: NavController,
    state: LazyListState,
    houseInfo: MutableState<HouseInfo?>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = state,
        modifier = modifier.selectableGroup(),
        contentPadding = PaddingValues(
            vertical = 10.dp
        )
    ) {
        items(elements) { element ->
            Column {
                HouseListElement(
                    name = stringResource(id = element.nameId),
                    description = stringResource(id = element.shortDescriptionId),
                    price = element.price,
                    painter = painterResource(id = element.images[0].resourceId),
                    onClick = {
                        houseInfo.value = element
                        navController.navigate(MainNavRoutes.HouseMenu.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
//                if(element != elements.last()) {
//                    HorizontalDivider(
//                        thickness = 1.dp,
//                        color = MaterialTheme.colorScheme.outline,
//                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//                    )
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListMenuPreview() {
    val houseInfo = remember {
        mutableStateOf<HouseInfo?>(null)
    }
    SaleAndRentTheme {
        ListMenu(
            houses,
            navController = rememberNavController(),
            rememberLazyListState(),
            houseInfo
        )
    }
}