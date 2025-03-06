package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current

    // Data Dummy
    val idPatient = "12345ABC"
    val role = "Patient"
    val fullName = "John Doe"
    val dateOfBirth = "01 January 1990"
    val homeAddress = "123 Main Street, City"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Title
        Text(
            text = "Profile",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Data
        ProfileItem(label = "Id Patient", value = idPatient, isCopyable = true, context)
        ProfileItem(label = "Role", value = role)
        ProfileItem(label = "Full Name", value = fullName)
        ProfileItem(label = "Date of Birth", value = dateOfBirth)
        ProfileItem(label = "Home Address", value = homeAddress)

        Spacer(modifier = Modifier.height(24.dp))

        // Edit & Logout Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* Handle Edit */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF164ABF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Edit", color = Color.White)
            }

            Button(
                onClick = { /* Handle Logout */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF164ABF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "LogOut", color = Color.White)
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String, isCopyable: Boolean = false, context: Context? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Text(text = value, fontSize = 14.sp)
//            if (isCopyable && context != null) {
//                Icon(
//                    imageVector = Icons.Default.ContentCopy,
//                    contentDescription = "Copy",
//                    modifier = Modifier
//                        .size(18.dp)
//                        .clickable {
//                            copyToClipboard(context, value)
//                        }
//                        .padding(start = 8.dp)
//                )
//            }
        }
    }
}

fun copyToClipboard(context: Context, text: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("Copied Text", text)
    clipboardManager.setPrimaryClip(clipData)

    // Tampilkan Toast sebagai konfirmasi
    Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
}
