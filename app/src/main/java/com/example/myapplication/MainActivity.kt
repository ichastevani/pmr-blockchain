package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapplication.data.UiEvent
import com.example.myapplication.screens.PermissionsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsState()
            val navController = rememberNavController()

            MyApplicationTheme {
                Scaffold(
                    bottomBar = {
                        if (shouldShowBottomBar(navController)) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavHost(navController = navController, startDestination = "wallet_connect_screen") {
                            composable("wallet_connect_screen") {
                                WalletConnectScreen(
                                    isConnecting = uiState.isConnecting,
                                    balance = uiState.balance,
                                    eventSink = viewModel::eventSink,
                                    navController = navController
                                )
                            }
                            composable("login_screen") { LoginScreen(navController) }
                            composable("signup_screen") { SignUpScreen(navController) }
                            composable("dashboard_screen") { DashboardScreen(navController) }
                            composable("record_screen") { RecordScreen(navController) }
                            composable("permissions_screen") { PermissionsScreen(navController) }
                            composable("activity_log_screen") { ActivityLogScreen(navController) }
                            composable("profile_screen") { ProfileScreen(navController) }
                            composable("add_new_record") { AddNewRecordScreen(navController) }
                        }
                    }
                }
            }

            // Handle UI Events
            LaunchedEffect(key1 = uiState.isConnecting) {
                viewModel.updateBalance()
            }

            OnEvent(events = viewModel.uiEvent) { event ->
                when (event) {
                    is UiEvent.Message -> {
                        Toast.makeText(this@MainActivity, event.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

/**
 * Menentukan apakah Bottom Navigation harus ditampilkan atau tidak.
 */
@Composable
fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentDestination !in listOf("wallet_connect_screen", "login_screen", "signup_screen")
}

/**
 * Komponen Bottom Navigation Bar
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navItems = listOf(
        Triple("Home", Icons.Default.Home, "dashboard_screen"),
        Triple("Record", Icons.Default.List, "record_screen"),
        Triple("Permissions", Icons.Default.Lock, "permissions_screen"),
        Triple("Activity", painterResource(id = R.drawable.baseline_history_24), "activity_log_screen"),
        Triple("Profile", Icons.Default.Person, "profile_screen")
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        tonalElevation = 4.dp,
        containerColor = androidx.compose.ui.graphics.Color(0xFF133E87) // Warna latar belakang
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        navItems.forEach { (label, icon, route) ->
            val isSelected = currentRoute == route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    when (icon) {
                        is ImageVector -> Icon(
                            imageVector = icon,
                            contentDescription = label,
                            modifier = Modifier.size(24.dp),
                            tint = if (isSelected) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color(0xFFB0C4DE) // Warna ikon
                        )
                        is Painter -> Icon(
                            painter = icon,
                            contentDescription = label,
                            modifier = Modifier.size(24.dp),
                            tint = if (isSelected) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color(0xFFB0C4DE) // Warna ikon
                        )
                    }
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        color = if (isSelected) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color(0xFFB0C4DE) // Warna teks
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "record_screen") {
        composable("record_screen") { RecordScreen(navController) }
        composable("add_new_record") { AddNewRecordScreen(navController) }
    }
}