package com.example.besttodolist.presentation.nav_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.besttodolist.R
import com.example.besttodolist.presentation.main_screen.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBar() {

    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()

    BottomAppBar(
        containerColor = Color(0xff223148),
        modifier = Modifier
            .height(60.dp)
            .clip(RoundedCornerShape(20.dp, topEnd = 30.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_home_outlined), contentDescription = "Home icon")

            FloatingActionButton(
                onClick = { mainScreenViewModel.upsertTodo("YES", "02-03-24") },
                contentColor = Color(0xfff7f7f7),
                shape = RoundedCornerShape(10.dp),
                containerColor = Color(0xff85aff7),
                modifier = Modifier
                    .size(40.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_plus), contentDescription = "Plus icon")
            }

            Icon(painter = painterResource(id = R.drawable.ic_calendar_outlined), contentDescription = "Calendar icon")
        }
    }
}