package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.data.EventSink
@Composable
fun WalletConnectScreen(
    isConnecting: Boolean,
    balance: String?,
    eventSink: (EventSink) -> Unit,
    navController: NavHostController // Menambahkan navController untuk navigasi
) {

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF1E3A8A)) {  // Background color
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Judul
            Text(
                "Access Your Personal Medical Record",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                "Stay in control of your medical data\nanytime, anywhere",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Kondisi ketika sudah terkoneksi (isConnecting == true)
            if (isConnecting) {
                Text(
                    "Balance: $balance",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = { eventSink(EventSink.Disconnect) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC407A))  // Red color for logout
                ) {
                    Text("Logout", color = Color.White, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))  // Adding space between Logout and Login button
                Button(
                    onClick = { navController.navigate("login_screen") },  // Navigasi ke login screen
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))  // Blue color for login
                ) {
                    Text("Login to App", color = Color.White, fontSize = 18.sp)
                }
            } else {
                // Kondisi ketika belum terkoneksi (isConnecting == false)
                Button(
                    onClick = { eventSink(EventSink.Connect) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))  // Blue color for connect
                ) {
                    Text("Connect Wallet", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LoggedInPreview() {
    MyApplicationTheme {
        WalletConnectScreen(
            isConnecting = true,
            balance = "100$",
            eventSink = {},
            navController = rememberNavController() // Add a preview NavController
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LoggedOutPreview() {
    MyApplicationTheme {
        WalletConnectScreen(
            isConnecting = false,
            balance = "null",
            eventSink = {},
            navController = rememberNavController() // Add a preview NavController
        )
    }
}
