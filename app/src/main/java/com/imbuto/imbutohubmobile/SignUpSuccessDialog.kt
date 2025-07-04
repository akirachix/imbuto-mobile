package com.imbuto.imbutohubmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun ConfirmationScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000)
        // Redirect after 3 seconds (simulate loading)
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Choose.route) { inclusive = false }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF14532D)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 8.dp,
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Success",
                    tint = Color(0xFF14532D),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text("Congratulations!", fontSize = 22.sp, color = Color(0xFF14532D))
                Text(
                    "Your account is ready to use. You will be redirected to homepage in a few seconds",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(12.dp))
                CircularProgressIndicator(color = Color(0xFF14532D))
            }
        }
    }
}

// Used in SignupScreen:
@Composable
fun ConfirmationDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Success",
                    tint = Color(0xFF14532D),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text("Congratulations!", fontSize = 20.sp, color = Color(0xFF14532D))
            }
        },
        text = {
            Text("Your account is ready to use. You will be redirected to homepage in a few seconds")
        },
        buttons = {
            Box(
                Modifier.fillMaxWidth().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF14532D))
            }
        }
    )
}