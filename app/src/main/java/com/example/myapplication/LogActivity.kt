package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

data class ActivityLog(
    val timestamp: String,
    val activityType: String,
    val module: String,
    val performedBy: String
)

@Composable
fun ActivityLogScreen(navController: NavController) {
    val logs = listOf(
        ActivityLog("10 March 2023, 14:00", "Access Request Submitted", "Vaccination Records", "Dr. John Doe"),
        ActivityLog("09 March 2023, 12:30", "Access Granted", "Radiology Reports", "Dr. Jane Smith")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Activity Log",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Table Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TableHeader("Timestamp", Modifier.weight(1.5f))
            TableHeader("Activity Type", Modifier.weight(2f))
            TableHeader("Module", Modifier.weight(1.5f))
            TableHeader("Performed By", Modifier.weight(1.5f))
        }

        Divider(color = Color.Black, thickness = 1.dp)

        // Table Rows
        logs.forEach { log ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(log.timestamp, Modifier.weight(1.5f))
                TableCell(log.activityType, Modifier.weight(2f))
                TableCell(log.module, Modifier.weight(1.5f))
                TableCell(log.performedBy, Modifier.weight(1.5f))
            }
        }
    }
}

@Composable
fun TableHeader(text: String, modifier: Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun TableCell(text: String, modifier: Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
private fun ActivityLogScreenPreview() {
    MyApplicationTheme {
        LoginScreen(navController = rememberNavController())
    }
}
