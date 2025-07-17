package com.Imbuto.imbutohub


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeaserFlow(onFinish: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }
    val teasers = listOf(
        Triple(
            R.drawable.imbuto_cow, // :cow2: cow.png in res/drawable
            "WELCOME to Imbuto Hub",
            "Streamline every step—from milk collection to payment—with Imbuto"
        ),
        Triple(
            R.drawable.farmer_cropped, // :male-farmer::skin-tone-5: farmer.png
            "Imbuto Hub",
            "Effortless milk delivery tracking for farmers and cooperatives"
        ),
        Triple(
            R.drawable.imbuto_user,
            "Imbuto Hub",
            "Track your cooperative's growth and progress at a glance"
        )
    )
    val (image, title, description) = teasers[currentPage]
    TeaserScreen(
        title = title,
        description = description,
        imageRes = image,
        currentPage = currentPage,
        totalPages = teasers.size,
        onNextClick = {
            if (currentPage < teasers.size - 1) {
                currentPage++
            } else {
                onFinish()
                           }
        }
    )
}


@Composable
fun TeaserScreen(
    title: String,
    description: String,
    imageRes: Int,
    currentPage: Int,
    totalPages: Int,
    onNextClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF003300)) // Dark green background
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                    .background(Color(0xFFFCF7EA)) // Cream color
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(totalPages) {
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .padding(top = 24.dp)
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (it == currentPage) Color(0xFFECB938)
                                        else Color(0xFF1B5E20)
                                    )
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = description,
                            fontSize = 16.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Button(
                            onClick = onNextClick,
                            modifier = Modifier.fillMaxWidth(fraction = 0.7f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = if (currentPage == totalPages - 1) "Get Started" else "Next",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
