package com.Imbuto.imbutohub
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun ResetPassword(
    onNext: () -> Unit,
    onGoBack: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val passwordError = validatePassword(password)
    val confirmError = validateConfirmPassword(password, confirmPassword)
    val isFormValid = passwordError == null && confirmError == null &&
            password.isNotBlank() && confirmPassword.isNotBlank()
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF003F21),
                topLeft = Offset(-300f, -300f),
                size = Size(size.width * 2, size.height * 2),
                cornerRadius = CornerRadius(100f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 65.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGoBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = "Go Back",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Reset Password",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Please enter your new password",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    isError = passwordError != null,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (passwordError != null) {
                    Text(
                        text = passwordError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    singleLine = true,
                    isError = confirmError != null,
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (confirmPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (confirmError != null) {
                    Text(
                        text = confirmError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { onNext() },
                    enabled = isFormValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.run { buttonColors(Color(0xFFFFC700)) }
                ) {
                    Text("Done", fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }
}
fun validatePassword(password: String): String? {
    return when {
        password.isBlank() -> null
        password.length < 8 -> "Must be at least 8 characters"
        !password.any { it.isUpperCase() } -> "Must include an uppercase letter"
        !password.any { it.isLowerCase() } -> "Must include a lowercase letter"
        !password.any { it.isDigit() } -> "Must include a number"
        else -> null
    }
}
fun validateConfirmPassword(password: String, confirm: String): String? {
    return if (confirm.isNotBlank() && password != confirm) {
        "Passwords do not match"
    } else null
}







@Composable
fun ForgotPassword(
    onPhoneSelected: () -> Unit,
    onEmailSelected: () -> Unit,
    onGoBack: () -> Unit = {}
) {
    var selectedOption by remember { mutableStateOf("") }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF003F21),
                topLeft = Offset(-300f, -300f),
                size = Size(size.width * 2, size.height * 2),
                cornerRadius = CornerRadius(100f, 100f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGoBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back",
                    tint = Color.White
                )
            }
            Text(
                text = "Go Back",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(38.dp))
                Text(
                    text = "Forgot password?",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Select which contact method to use for password recovery",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "phone",
                        onClick = { selectedOption = "phone" }
                    )
                    Text("Phone number", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "email",
                        onClick = { selectedOption = "email" }
                    )
                    Text("Email address", fontSize = 16.sp, fontWeight = FontWeight.Bold )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Button(
                    onClick = {
                        when (selectedOption) {
                            "phone" -> onPhoneSelected()
                            "email" -> onEmailSelected()
                            else -> Toast.makeText(
                                context,
                                "Please select one method",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC700))
                ) {
                    Text("Continue", color = Color.Black, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}









@Composable
fun PasswordRecovery(
    onReset: () -> Unit = {},
    onGoBack: () -> Unit = {},
) {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF003F21),
                topLeft = Offset(-300f, -300f),
                size = Size(size.width * 2, size.height * 2),
                cornerRadius = CornerRadius(100f, 100f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGoBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back",
                    tint = Color(0xFFFFFFFF)
                )
            }
            Text(
                text = "Go Back",
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(38.dp))
                Text(
                    text = "Password recovery",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Enter your phone number to recover your password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { if (it.length <=10 && it.all { char -> char.isDigit() }) phoneNumber = it},
                    label = { Text("07...") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            tint = Color.Black
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 36.dp),
                    shape = RoundedCornerShape(15.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = {

                        onReset()
                    },
                    enabled = phoneNumber.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC700))
                ) {
                    Text("Reset", color = Color.Black, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun PasswordRecoveryEmail(
    onReset: () -> Unit = {},
    onGoBack: () -> Unit = {},
    onEmailSubmitted: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    val isEmailValid = remember(email) { email.contains("@") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF003F21),
                topLeft = Offset(-300f, -300f),
                size = Size(size.width * 2, size.height * 2),
                cornerRadius = CornerRadius(100f, 100f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGoBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back",
                    tint = Color(0xFFFFFFFF)
                )
            }
            Text(
                text = "Go Back",
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Password recovery",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                        .padding(top = 24.dp)
                )
                Text(
                    text = "Enter your email to recover your password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("email@imubuto.com") },
                    isError = !isEmailValid,
                    supportingText = {
                        if (!isEmailValid) {
                            Text(text = "Invalid email format")
                        }
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = Color(0xFF003F21)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 36.dp),
                    shape = RoundedCornerShape(15.dp)
                )
                Button(
                    onClick = {
                        Toast
                            .makeText(context, "Reset clicked", Toast.LENGTH_SHORT)
                            .show()
                        onReset()

                    },
                    enabled = email.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC700))
                ) {
                    Text("Reset", color = Color.Black, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun PasswordResetSuccess(
    onContinueClicked: () -> Unit,
    onGoBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = Color(0xFF003F21), // Green
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your password has been reset successfully!",

                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onContinueClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.run {
                    val buttonColors = buttonColors(
                        Color(0xFFFFC700), Color.Black, // Orange and Black// Dark green
                    )
                    buttonColors
                }
            ) {
                Text(text = "Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(onClick = onGoBackClicked) {
                Text("Go Back", color = Color(0xFF003F21), fontSize = 16.sp)
            }
        }
    }
}
@Composable
fun VerificationScreen(
    codeLength: Int = 4,
    codeExpires: String = "03:00",
    onGoBack: () -> Unit = {},
    onVerify: () -> Unit = {},
) {
    val context = LocalContext.current
    val codeDigits = remember { mutableStateListOf("", "", "", "") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF003F21),
                topLeft = Offset(-300f, -300f),
                size = Size(size.width * 2, size.height * 2),
                cornerRadius = CornerRadius(100f, 100f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGoBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back",
                    tint = Color(0xFFFFFFFF)
                )
            }
            Text(
                text = "Go Back",
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(38.dp))
                Text(
                    text = "Check your phone",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "We’ve sent the code to your phone",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(codeLength) { idx ->
                        OutlinedTextField(
                            value = codeDigits[idx],
                            onValueChange = { value ->
                                if (value.length <= 1 && value.all { it.isDigit() }) {
                                    codeDigits[idx] = value
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .padding(horizontal = 6.dp),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp)

                        )
                    }
                }
                Text(
                    text = "Code expires in $codeExpires",
                    fontSize = 18.sp,
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 36.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(
                        onClick = {
                            onVerify()
                        },
                        enabled = codeDigits.all { it.isNotEmpty() },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(start = 7.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC700))
                    ) {
                        Text("Verify", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}