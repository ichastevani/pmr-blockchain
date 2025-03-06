package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecordScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Judul halaman
        Text(
            text = "My Medical Record",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search bar
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle search */ },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            placeholder = { Text("Search Records") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F4FF), RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row untuk tombol "Add New Record" dan Filter Dropdown
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Button "Add New Record"
            Button(
                onClick = { /* Navigate to Add Record Screen */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF164ABF))
            ) {
                Text(text = "Add New Record", color = Color.White)
            }

            // Dropdown untuk Filter Record
            var expanded by remember { mutableStateOf(false) }
            var selectedFilter by rememberSaveable { mutableStateOf("Filter Records") }

            Box {
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF164ABF))
                ) {
                    Text(text = selectedFilter, color = Color.White)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("All Records", "Recent Records", "Old Records").forEach { filter ->
                        DropdownMenuItem(
                            text = { Text(filter) },
                            onClick = {
                                selectedFilter = filter
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
