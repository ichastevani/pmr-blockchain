package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PermissionsScreen(navController: NavController? = null) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Notification", "Access History")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Permissions", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tabs.forEachIndexed { index, title ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = index }
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF121826)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (selectedTab == index) {
                        Box(
                            modifier = Modifier
                                .height(3.dp)
                                .width(40.dp)
                                .background(Color(0xFF133E87))
                        )
                    }
                }
            }
        }


        when (selectedTab) {
            0 -> NotificationList()
            1 -> AccessHistoryList()
        }
    }
}

@Composable
fun NotificationList() {
    val notifications = listOf(
        NotificationItem("Dr. John Doe", "Vaccination Records"),
        NotificationItem("Dr. Alex", "Vaccination Records")
    )

    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
        notifications.forEach { data ->
            NotificationCard(data)
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = notification.doctorName, fontWeight = FontWeight.Bold)
                Text(text = "04:32 pm", fontSize = 12.sp, color = Color.Gray)
            }
            Text(text = "Requested access to your ${notification.recordType}", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { /* Handle approve view */ },
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("View")
                }
                Button(
                    onClick = { /* Handle approve edit */ },
                    colors = ButtonDefaults.buttonColors(Color.DarkGray),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = { /* Handle reject */ },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Reject")
                }
            }
        }
    }
}

@Composable
fun AccessHistoryList() {
    val history = listOf(
        NotificationItem("Dr. John Doe", "Vaccination Records"),
        NotificationItem("Dr. Alex", "Vaccination Records")
    )

    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
        history.forEach { data ->
            AccessHistoryCard(data)
        }
    }
}

@Composable
fun AccessHistoryCard(history: NotificationItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = history.doctorName, fontWeight = FontWeight.Bold)
                Text(text = "04:32 pm", fontSize = 12.sp, color = Color.Gray)
            }
            Text(text = "Access to your ${history.recordType}", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* Handle revoke access */ }, colors = ButtonDefaults.buttonColors(Color.Red)) {
                Text("Revoke Access")
            }
        }
    }
}

data class NotificationItem(val doctorName: String, val recordType: String)

@Preview(showBackground = true)
@Composable
fun PreviewPermissionsScreen() {
    PermissionsScreen()
}
