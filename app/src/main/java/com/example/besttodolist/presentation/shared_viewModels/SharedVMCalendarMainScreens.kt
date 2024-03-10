package com.example.besttodolist.presentation.shared_viewModels

import androidx.lifecycle.ViewModel
import com.example.besttodolist.presentation.calendar_screen.toMillis
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SharedVMCalendarMainScreens: ViewModel() {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var chosenDate: String = LocalDateTime.now().format(formatter)

    var selectedDate: Long = LocalDateTime.now().toMillis()

    //Used in main screen to check does today date corresponds to chosen date
    val todayDate: String = LocalDateTime.now().format(formatter)

    fun updateChosenDate(date: String) {
        chosenDate = date
    }

    fun updateSelectedDate(date: Long) {
        selectedDate = date
    }

}