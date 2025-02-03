package com.project.saleandrent

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.saleandrent.mainactvity.BuyMenu
import com.project.saleandrent.mainactvity.HouseMenuWrapper
import com.project.saleandrent.mainactvity.ListMenu
import com.project.saleandrent.mainactvity.houses
import com.project.saleandrent.elements.HouseInfo

import com.project.saleandrent.elements.MainNavRoutes
import com.project.saleandrent.elements.rememberMutableStateListOf
import com.project.saleandrent.ui.theme.SaleAndRentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (isUserLoggedIn()) {
            setContent {
                SaleAndRentTheme {
                    Main(modifier = Modifier)
                }
            }
        } else {
            val intent = Intent(this, EnterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val loggedIn = intent.getBooleanExtra("Login", false)
        return loggedIn
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentRoute = rememberSaveable { mutableStateOf<MainNavRoutes?>(null) }
    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val houseInfo = rememberSaveable {
        mutableStateOf<HouseInfo?>(
            null
        )
    }
    val purchases = rememberMutableStateListOf<HouseInfo>()

    Scaffold(modifier = modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainTopBar(
                scrollBehavior = scrollBehavior,
                navController = navController,
                currentRoute = currentRoute,
                currentHouseName = houseInfo.value?.let { stringResource(it.nameId) }
            )
        },
        bottomBar = {
            MainBottomBar(
                onListClick = {
                    navController.navigate(MainNavRoutes.ListMenu.route)
                },
                onShopClick = {
                    navController.navigate(MainNavRoutes.BuyMenu.route)
                },
                currentRoute = currentRoute
            )
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainNavRoutes.ListMenu.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainNavRoutes.ListMenu.route) {
                ListMenu(
                    houses, navController,
                    state = scrollState,
                    houseInfo = houseInfo
                )
            }
            composable(MainNavRoutes.HouseMenu.route) {
                houseInfo.value?.let { it1 ->
                    HouseMenuWrapper(
                        modifier = Modifier,
                        houseInfo = it1,
                        purchases = purchases
                    )
                }
            }
            composable(MainNavRoutes.BuyMenu.route) {
                BuyMenu(purchases = purchases,
                    navController = navController)
            }
        }
    }
    DisposableEffect(key1 = navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = MainNavRoutes.getByRoute(destination.route)
        }
        onDispose {}
    }
    navController.addOnDestinationChangedListener { _, _, _ ->
        scrollBehavior.state.contentOffset = 0f
        scrollBehavior.state.heightOffset = 0f
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    currentRoute: MutableState<MainNavRoutes?>,
    currentHouseName: String?
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = when (currentRoute.value?.route) {
                    MainNavRoutes.ListMenu.route -> MainNavRoutes.ListMenu.header
                    MainNavRoutes.HouseMenu.route -> currentHouseName ?: "House"
                    MainNavRoutes.BuyMenu.route -> MainNavRoutes.BuyMenu.header
                    else -> MainNavRoutes.ListMenu.header
                }
            )

        }
    )
}

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    onListClick: () -> Unit,
    onShopClick: () -> Unit,
    currentRoute: MutableState<MainNavRoutes?>
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = colorResource(id = R.color.main),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onListClick,
                shape = CircleShape,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentRoute.value?.route == MainNavRoutes.ListMenu.route)
                        colorResource(id = R.color.main)
                    else MaterialTheme.colorScheme.onBackground
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.List,
                        contentDescription = "List",
                        modifier = Modifier
                    )
                    Text(text = "List", style = MaterialTheme.typography.bodySmall)
                }
            }
            TextButton(
                onClick = onShopClick,
                shape = CircleShape,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentRoute.value?.route == MainNavRoutes.BuyMenu.route)
                        colorResource(id = R.color.main)
                    else MaterialTheme.colorScheme.onBackground
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "Shop",
                        modifier = Modifier
                    )
                    Text(text = "Cart", style = MaterialTheme.typography.bodySmall)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SaleAndRentTheme {
        Main()
    }
}