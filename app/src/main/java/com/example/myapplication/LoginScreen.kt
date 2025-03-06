package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    var password by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF608BC1)) { // Updated Background Color
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Access Your Personal Medical Record",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center, // Pusatkan teks
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            Text(
                "Stay in control of your medical data\nanytime, anywhere",
                fontSize = 13.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(12.dp), // Menambahkan sudut melengkung
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )


            Button(
                onClick = {
                    // Logika autentikasi di sini (jika ada)
                    navController.navigate("dashboard_screen") {
                        popUpTo("login_screen") { inclusive = true } // Mencegah kembali ke login setelah sukses
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))
            ) {
                Text("Login", color = Color.White, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            val signUpText = buildAnnotatedString {
                append("Don't have an account? ")
                pushStringAnnotation(tag = "signup", annotation = "signup_screen")
                withStyle(style = SpanStyle(color = Color(0xFF1D4ED8), textDecoration = TextDecoration.Underline)) {
                    append("Sign up here")
                }
                pop()
            }

            ClickableText(
                text = signUpText,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                onClick = { offset ->
                    signUpText.getStringAnnotations(tag = "signup", start = offset, end = offset)
                        .firstOrNull()?.let {
                            navController.navigate("signup_screen")
                        }
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