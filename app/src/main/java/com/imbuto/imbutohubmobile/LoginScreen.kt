package com.imbuto.imbutohubmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF14532D)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Login to Mbuto Hub",
                fontSize = 24.sp,
                color = Color(0xFF14532D),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text("Email / Username") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password, onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { /* TODO: Forgot password action */ }) {
                Text("Forgot password?", color = Color.Gray)
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { /* TODO: Login action */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF14532D)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login", color = Color.White)
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don't have an account?", color = Color.Gray)
                TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
                    Text("Create account", color = Color(0xFF14532D))
                }
            }
        }
    }
}