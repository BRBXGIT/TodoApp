package com.example.besttodolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Upsert
    suspend fun upsertTodo(todo: Todo)

    @Update
    suspend fun updateTodoIsCompleted(todo: Todo)

    @Update
    suspend fun updateTodoIsInBookmark(todo: Todo)

    @Query("SELECT * FROM todo WHERE date = :date AND isCompleted = 0")
    fun getUncompletedTodosByDate(date: String): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE date = :date AND isCompleted = 1")
    fun getCompletedTodosByDate(date: String): Flow<List<Todo>>
}