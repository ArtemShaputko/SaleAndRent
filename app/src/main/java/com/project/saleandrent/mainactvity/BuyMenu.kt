package com.project.saleandrent.mainactvity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.project.saleandrent.elements.HouseInfo
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.saleandrent.R
import com.project.saleandrent.elements.MainNavRoutes
import com.project.saleandrent.ui.theme.SaleAndRentTheme
import com.project.saleandrent.ui.theme.Typography

@Composable
fun BuyMenu(purchases: SnapshotStateList<HouseInfo>, navController: NavController) {
    if(purchases.isEmpty()) {
        Box(Modifier.fillMaxSize()) {
            Text(
                text = "Cart is empty",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    LazyColumn {
        items(purchases) { purchase ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = stringResource(id = purchase.nameId), style = Typography.bodyLarge)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "$${purchase.price}", style = Typography.bodySmall)
                }
                TextButton(
                    modifier = Modifier,
                    onClick = {
                        navController.navigate(MainNavRoutes.ListMenu.route)
                        purchases.remove(purchase)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.second).copy(0.5f),
                        contentColor = Color.White,
                        disabledContentColor = colorResource(id = R.color.second).copy(0.5f),
                        disabledContainerColor = Color.White
                    )
                ) {
                    Text(text = "checkout")
                }
            }
            if (purchase != purchases.last()) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuyMenuPreview() {
    SaleAndRentTheme {
    }
}