package com.example.besttodolist.presentation.calendar_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.besttodolist.presentation.nav_bar.BottomBar
import com.example.besttodolist.presentation.shared_viewModels.SharedVMCalendarMainScreens
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(
    navController: NavHostController,
    sharedVMCalendarMainScreens: SharedVMCalendarMainScreens
) {

    //State for date picker
    val datePickerState = rememberDatePickerState(
        yearRange = (2024..2041),
        initialSelectedDateMillis = sharedVMCalendarMainScreens.selectedDate,
        initialDisplayMode = DisplayMode.Picker,
        initialDisplayedMonthMillis = null,
    )

    Scaffold(
        bottomBar = { BottomBar(
            navController = navController,
            sharedVMCalendarMainScreens = sharedVMCalendarMainScreens
        ) }
    ) {

        //Main box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff162232))
        ) {
            //Update date in main and calendar screens
            val chosenDate = convertMillisToDate(datePickerState.selectedDateMillis!!)
            sharedVMCalendarMainScreens.updateChosenDate(chosenDate)
            sharedVMCalendarMainScreens.updateSelectedDate(datePickerState.selectedDateMillis!!)

            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Color(0xff85aff7),
                    todayDateBorderColor = Color(0xff85aff7),
                    todayContentColor = Color(0xff85aff7),
                )
            )
        }
    }
}

//Convert LocalDateTime format to millis
fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

//Convert millis to readable string format
@SuppressLint("SimpleDateFormat")
private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}