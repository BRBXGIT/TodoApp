package com.example.besttodolist.screens.main_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen() {
    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
}