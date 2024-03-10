package com.example.besttodolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val isCompleted: Boolean,
    val date: String,
    val isInBookmark: Boolean
)
