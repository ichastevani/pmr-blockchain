package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DashboardScreen(navController: NavHostController) {
    var selectedTab by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E3A8A))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Hi, \"name\"",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Your records, your control",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        // Konten berdasarkan tab yang dipilih
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> Text("Home Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
                1 -> Text("Record Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
                2 -> Text("Permissions Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
                3 -> Text("Log Activity Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
                4 -> Text("Profile Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
            }
        }

        // Daftar tab navigasi (menggunakan drawable untuk History)
        val navItems = listOf(
            "Home" to Icons.Default.Home,
            "Record" to Icons.Default.List,
            "Permissions" to Icons.Default.Lock,
            "Activity" to painterResource(id = R.drawable.baseline_history_24), // Pakai drawable
            "Profile" to Icons.Default.Person
        )

        // Bottom Navigation Bar
        NavigationBar(
            modifier = Modifier.fillMaxWidth().height(70.dp), // Tinggikan navbar
            tonalElevation = 4.dp
        ) {
            navItems.forEachIndexed { index, (label, icon) ->
                NavigationBarItem(
                    icon = {
                        if (icon is androidx.compose.ui.graphics.vector.ImageVector) {
                            Icon(icon, contentDescription = label, modifier = Modifier.size(24.dp))
                        } else {
                            Icon(
                                painter = icon as androidx.compose.ui.graphics.painter.Painter,
                                contentDescription = label,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = label,
                            fontSize = 10.sp, // Ukuran font lebih kecil
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            softWrap = false // Pastikan teks tidak dipotong
                        )
                    },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    alwaysShowLabel = true
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    MyApplicationTheme {
        DashboardScreen(navController = rememberNavController())
    }
}