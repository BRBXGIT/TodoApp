package com.example.besttodolist.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.besttodolist.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(Color(0xff122233))
    systemUiController.setStatusBarColor(Color(0xff122233))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff122233))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.13f),
            contentAlignment = Alignment.Center
        ) {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sign_in_picture),
                contentDescription = "Sign in picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 18.sp)) {
                        append("Organize your ")
                    }
                    withStyle(SpanStyle(color = Color(0xff6699CC), fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                        append("life")
                    }
                    withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 18.sp)) {
                        append(" work and finally.")
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff2C4157),
                    contentColor = Color(0xffD3D3D3)
                )
            ) {
                Text(text = "Continue with Google", textAlign = TextAlign.Center, fontSize = 16.sp)
            }

            Button(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff2C4157),
                    contentColor = Color(0xffD3D3D3)
                )
            ) {
                Text(text = "Continue with GitHub", textAlign = TextAlign.Center, fontSize = 16.sp)
            }
        }
    }
}