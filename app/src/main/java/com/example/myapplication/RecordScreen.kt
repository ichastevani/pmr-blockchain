package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecordScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf("Type Record") }
    var searchText by remember { mutableStateOf("") }
    var showDetailScreen by remember { mutableStateOf(false) }
    var selectedRecord by remember { mutableStateOf<Pair<String, String>?>(null) }

    val filterOptions = listOf("Type Record", "Vaccination", "Radiology", "Diagnostic", "Prescription", "Surgical", "Allergy")

    val allRecords = listOf(
        "Vaccination Records" to "COVID-19 Vaccine",
        "Radiology Reports" to "X-rays, MRIs, CT scans",
        "Diagnostic Tests" to "Blood tests, Urinalysis",
        "Prescriptions" to "Medications and dosages",
        "Surgical Records" to "Past surgeries or procedures",
        "Allergy Records" to "Known allergies and reactions"
    )

    val filteredRecords = allRecords.filter {
        (selectedFilter == "Type Record" || it.first.contains(selectedFilter, ignoreCase = true)) &&
                (searchText.isEmpty() || it.first.contains(searchText, ignoreCase = true))
    }

    if (showDetailScreen && selectedRecord != null) {
        RecordDetailScreen(
            title = selectedRecord!!.first,
            description = selectedRecord!!.second
        ) {
            showDetailScreen = false
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FB))
                .padding(16.dp)
        ) {
            Text(
                text = "My Medical Record",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F1F1F)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search Records") },
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        // TODO: Add New Record
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1D4ED8)
                    )
                ) {
                    Text("Add New Record")
                }

                Box {
                    Button(
                        onClick = { expanded = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1D4ED8)
                        )
                    ) {
                        Text(selectedFilter)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        filterOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedFilter = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredRecords.size) { index ->
                    val record = filteredRecords[index]
                    RecordCard(
                        title = record.first,
                        description = record.second
                    ) {
                        selectedRecord = record
                        showDetailScreen = true
                    }
                }
            }
        }
    }
}

@Composable
fun RecordCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = description, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun RecordDetailScreen(
    title: String,
    description: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F1F1F)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Description",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = ": $description",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Download File Radiology",
            fontSize = 14.sp,
            color = Color(0xFF1D4ED8),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                // TODO: Handle download action
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // TODO: Handle edit action
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1D4ED8)
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Edit")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Back")
        }
    }
}
