package com.Imbuto.imbutohub


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SignupScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val genderOptions = listOf("Male", "Female", "Other")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF003F21))
    ) {
        Column(Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF003F21)),
                contentAlignment = Alignment.Center
            ) {


                Row(
                    modifier = Modifier.fillMaxSize().padding(start = 16.dp, top = 14.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logoo),
                        contentDescription = "Imbuto Hub Logo",
                        modifier = Modifier
                            .size(65.dp),
                        contentScale = ContentScale.Fit
                    )

                }
                Text(
                    text = "Create Account",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(Modifier.height(4.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Spacer(Modifier.height(12.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text("First Name", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                            placeholder = { Text("e.g John", fontSize = 16.sp) },
                            singleLine = true,
                            shape = RoundedCornerShape(22.dp),
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                            placeholder = { Text("e.g Otieno", fontSize = 16.sp) },
                            singleLine = true,
                            shape = RoundedCornerShape(22.dp),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone number", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                        placeholder = { Text("e.g 078xxx", fontSize = 16.sp) },
                        singleLine = true,
                        shape = RoundedCornerShape(22.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email address", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                        placeholder = { Text("e.g johndoe@gmail.com", fontSize = 16.sp) },
                        singleLine = true,
                        shape = RoundedCornerShape(22.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = gender,
                                onValueChange = {},
                                label = { Text("Gender", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                                placeholder = { Text("Enter", fontSize = 16.sp) },
                                singleLine = true,
                                readOnly = true,
                                shape = RoundedCornerShape(22.dp),
                                trailingIcon = {
                                    IconButton(onClick = { genderExpanded = !genderExpanded }) {
                                        Icon(
                                            imageVector = if (genderExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            DropdownMenu(
                                expanded = genderExpanded,
                                onDismissRequest = { genderExpanded = false }
                            ) {
                                genderOptions.forEach {
                                    DropdownMenuItem(
                                        text = { Text(it, fontSize = 18.sp) },
                                        onClick = {
                                            gender = it
                                            genderExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                        OutlinedTextField(
                            value = location,
                            onValueChange = { location = it },
                            label = { Text("Location", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                            placeholder = { Text("e.g Sabatia", fontSize = 16.sp) },
                            singleLine = true,
                            shape = RoundedCornerShape(22.dp),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                        placeholder = { Text("e.g abc123", fontSize = 16.sp) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(22.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm Password", fontSize = 18.sp, fontWeight = FontWeight.Medium) },
                        placeholder = { Text("e.g abc123", fontSize = 16.sp) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(22.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(28.dp))

                    Button(
                        onClick = {
                            navController.navigate("success") {
                                popUpTo("signup") { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003F21)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),


                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Create Account", color = Color.White, fontSize = 20.sp)
                    }
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Already have an account? Login",
                        color = Color(0xFF15622C),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .clickable {
                                navController.navigate("login") {
                                    popUpTo("signup") { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                    )

                }
            }
        }
    }
}