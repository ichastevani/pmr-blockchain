package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SignUpScreen(navController: NavHostController) {
    // Define states to store input values
    var fullName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var homeAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Doctor") } // Default role

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Create Your Account",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                "Sign up to take full control of your medical data",
                fontSize = 16.sp,
                color = Color(0xFF1E3A8A),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )


            // Full name input
            TextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = { Text("Full name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            // Date of Birth input
            TextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                placeholder = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            // Home address input
            TextField(
                value = homeAddress,
                onValueChange = { homeAddress = it },
                placeholder = { Text("Home address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            // Password input
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
            // Doctor or Patient radio buttons
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Doctor", color = Color(0xFF1E3A8A))
                RadioButton(
                    selected = selectedRole == "Doctor",
                    onClick = { selectedRole = "Doctor" }
                )
                Text("Patient", color = Color(0xFF1E3A8A))
                RadioButton(
                    selected = selectedRole == "Patient",
                    onClick = { selectedRole = "Patient" }
                )
            }

            Button(
                onClick = { /* Handle Sign Up logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))  // Blue color for sign up
            ) {
                Text("Sign Up", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text to navigate back to Login
            Text(
                "Already have an account? Log in here",
                color = Color(0xFF1E3A8A),
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navController.navigate("login_screen")  // Navigate to Login screen
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SignUpScreenPreview() {
    MyApplicationTheme {
        SignUpScreen(navController = rememberNavController())
    }
}

