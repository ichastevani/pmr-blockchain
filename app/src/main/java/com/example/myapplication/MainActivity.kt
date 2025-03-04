package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.UiEvent
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            // Create a NavController for navigation
            val navController = rememberNavController()

            // Use only one NavHost to manage navigation between screens
            NavHost(navController = navController, startDestination = "wallet_connect_screen") {
                composable("wallet_connect_screen") {
                    WalletConnectScreen(
                        isConnecting = uiState.isConnecting,
                        balance = uiState.balance,
                        eventSink = viewModel::eventSink,
                        navController = navController // Pass navController for navigation
                    )
                }
                composable("login_screen") {
                    LoginScreen(navController = navController) // Navigate to Login Screen
                }
                composable("signup_screen") {
                    SignUpScreen(navController = navController) // Navigate to Sign Up Screen
                }
            }

            // Handle any UI events (e.g., showing Toast messages)
            LaunchedEffect(key1 = uiState.isConnecting) {
                viewModel.updateBalance()
            }

            OnEvent(events = viewModel.uiEvent) { event ->
                when (event) {
                    is UiEvent.Message -> {
                        Toast.makeText(
                            this@MainActivity,
                            event.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            MyApplicationTheme {
                // The UI for your app is already handled by the NavHost above
            }
        }
    }
}
