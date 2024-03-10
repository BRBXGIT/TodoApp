package com.example.besttodolist.presentation.sign_in

import android.widget.Toast
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.besttodolist.R
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    systemUiController: SystemUiController
) {

    //Change system bars colors
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xff162232))
    }

    //Check errors during signing in
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInErrorMessage) {
        state.signInErrorMessage?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val fontForLogo = FontFamily(Font(R.font.protestriot_regular))

    //Main column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff162232))
            .padding(start = 20.dp, end = 20.dp)
    ) {
        //Box with logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.13f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Todo List",
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
                painter = painterResource(id = R.drawable.sign_in_illustration),
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
                        append("Organize your ")
                    }
                    withStyle(SpanStyle(color = Color(0xff7ca8f0), fontSize = 17.sp, fontWeight = FontWeight.Bold)) {
                        append("life")
                    }
                    withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 17.sp)) {
                        append(" work and finally.")
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
            //Sign in with google button
            Button(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
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
                        painter = painterResource(id = R.drawable.ic_sign_in_google),
                        contentDescription = "sign in with google icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(text = "Continue with Google", textAlign = TextAlign.Center, fontSize = 16.sp)
                }
            }

            //Sign in with github button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
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
                        painter = painterResource(id = R.drawable.ic_sign_in_git),
                        contentDescription = "sign in with google icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(text = "Continue with GitHub", textAlign = TextAlign.Center, fontSize = 16.sp)
                }
            }
        }

        //Box with terms of privacy policy
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "By continue you agree to Todolist's",
                    fontSize = 13.sp,
                    color = Color(0xff808587),
                    fontWeight = FontWeight.Thin
                )
                HyperlinkText(
                    fullText = "Terms of Service and privacy policy",
                    linkText = listOf("Terms of Service", "privacy policy"),
                    hyperlinks = listOf("https://github.com/BRBXGIT", "https://github.com/BRBXGIT"),
                    fontSize = 13.sp,
                    linkTextColor = Color(0xff808587),
                    linkTextFontWeight = FontWeight.Thin,
                    linkTextDecoration = TextDecoration.Underline,
                    fullTextColor = Color(0xff808587)
                )
            }
        }
    }
}

//Hyper link text composable function
@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    fullTextColor: Color,
    linkText: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String> = listOf(),
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.Thin,
                color = fullTextColor
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}