package com.imbuto.imbutoauth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController



@Composable
fun WelcomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF003F21))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoWithText()

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate(AuthDestinations.LOGIN) },
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF003200)
                ),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    bottomStart = 12.dp,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = { navController.navigate(AuthDestinations.SIGNUP) },
                modifier = Modifier.fillMaxWidth(0.7f),
                border = BorderStroke(2.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    bottomStart = 12.dp,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp
                )
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun LogoWithText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.imbutologo),
                contentDescription = "Imbuto Hub Logo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "mbuto",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Text(
            text = "Hub",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}
