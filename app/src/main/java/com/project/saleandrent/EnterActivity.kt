package com.project.saleandrent

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.saleandrent.elements.EnterNavRoutes
import com.project.saleandrent.enteractivity.First
import com.project.saleandrent.enteractivity.LoginScreen
import com.project.saleandrent.enteractivity.SignupScreen
import com.project.saleandrent.ui.theme.SaleAndRentTheme

class EnterActivity : ComponentActivity() {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun isEmailValid(email: String): Boolean
    private external fun isPasswordValid(password: String): Boolean

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            SaleAndRentTheme {
                    Enter(
                        context = this,
                        isPasswordValid = { password ->
                            isPasswordValid(password)
                        },
                        isEmailValid = { email ->
                            isEmailValid(email)
                        },
                        modifier = Modifier
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Enter(
    context: Context,
    isPasswordValid: (String) -> Boolean,
    isEmailValid: (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentRoute = rememberSaveable { mutableStateOf<String?>(EnterNavRoutes.First.route) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(modifier = modifier
        .fillMaxSize(),
        topBar = {
            if (currentRoute.value != EnterNavRoutes.First.route) {
                EnterTopBar(
                    scrollBehavior = scrollBehavior,
                    navController = navController
                )
            }
        }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = EnterNavRoutes.First.route
        ) {
            composable(EnterNavRoutes.First.route) {
                First(onLogInPressed = {
                    navController.navigate(EnterNavRoutes.Login.route)
                }, onSignUpPressed = {
                    navController.navigate(EnterNavRoutes.Signup.route)
                })
            }
            composable(EnterNavRoutes.Login.route) {
                LoginScreen(context, isPasswordValid)
            }
            composable(EnterNavRoutes.Signup.route) {
                SignupScreen(context, isPasswordValid, isEmailValid)
            }
        }
    }
    DisposableEffect(key1 = navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route
        }
        onDispose {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
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
            Row {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home"
                )
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            }
        }
    )
}
