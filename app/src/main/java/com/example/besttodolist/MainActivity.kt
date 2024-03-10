package com.example.besttodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.besttodolist.presentation.nav_bar.NavGraph
import com.example.besttodolist.presentation.sign_in.GoogleAuthUiClient
import com.example.besttodolist.ui.theme.BestTodoListTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BestTodoListTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, googleAuthUiClient = googleAuthUiClient)
            }
        }
    }
}