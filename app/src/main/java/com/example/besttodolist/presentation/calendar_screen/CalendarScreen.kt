package com.example.besttodolist.presentation.calendar_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.besttodolist.R
import com.example.besttodolist.presentation.nav_bar.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(
    navController: NavHostController
) {
    val fontForLogo = FontFamily(Font(R.font.protestriot_regular))

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        //Main column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff162232))
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.13f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Calendar",
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontForLogo,
                    fontSize = 25.sp,
                    color = Color(0xff85aff7)
                )
            }

            //Box with picture
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar_screen_illustration),
                    contentDescription = "Sign in picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            //Box with text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 17.sp)) {
                            append("Here you can choose ")
                        }
                        withStyle(SpanStyle(color = Color(0xff7ca8f0), fontSize = 17.sp, fontWeight = FontWeight.Bold)) {
                            append("date")
                        }
                        withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 17.sp)) {
                            append(" and ")
                        }
                        withStyle(SpanStyle(color = Color(0xff7ca8f0), fontSize = 17.sp, fontWeight = FontWeight.Bold)) {
                            append("time")
                        }
                    }
                )
            }

            //Column with buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Choose date color
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff223148),
                        contentColor = Color(0xffD3D3D3)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_choose_date),
                            contentDescription = "Calendar icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Text(text = "Choose date", textAlign = TextAlign.Center, fontSize = 16.sp)
                    }
                }

                //Choose time button
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff223148),
                        contentColor = Color(0xffD3D3D3)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_choose_time),
                            contentDescription = "Clock icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Text(text = "Choose time", textAlign = TextAlign.Center, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}