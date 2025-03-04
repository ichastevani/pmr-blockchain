package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun LoginScreen(navController: NavHostController) {
    // Define state to store the value of the password
    var password by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title and description
            Text(
                "Access Your Personal Medical Record",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                "Stay in control of your medical data\nanytime, anywhere",
                fontSize = 16.sp,
                color = Color(0xFF1E3A8A),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Password input field
            TextField(
                value = password, // Bind the value to the password state
                onValueChange = { password = it }, // Update the state when text changes
                placeholder = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )

            // Login button
            Button(
                onClick = { /* Handle Login logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))  // Blue color for login
            ) {
                Text("Login", color = Color.White, fontSize = 18.sp)
            }

            // Sign up prompt
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Don't have an account? Sign up here",
                color = Color(0xFF1E3A8A),
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navController.navigate("signup_screen")  // Navigate to Sign Up screen
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LoginScreenPreview() {
    MyApplicationTheme {
        LoginScreen(navController = rememberNavController())
    }
}
