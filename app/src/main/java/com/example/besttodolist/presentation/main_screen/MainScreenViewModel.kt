package com.example.besttodolist.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.besttodolist.data.Todo
import com.example.besttodolist.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val todoDao: TodoDao
): ViewModel() {

    //Upsert new to_do
    fun upsertTodo(title: String, date: String, isInBookmark: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.upsertTodo(
                Todo(
                    0, title,
                    false, date, isInBookmark
                )
            )
        }
    }

    //Update isCompleted parameter
    fun updateTodoIsCompleted(
        id: Int, title: String, date: String,
        isCompleted: Boolean, isInBookmark: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateTodoIsCompleted(
                Todo(id, title, isCompleted, date, isInBookmark)
            )
        }
    }

    //Update isInBookmark parameter
    fun updateTodoIsInBookmark(
        id: Int, title: String, date: String,
        isCompleted: Boolean, isInBookmark: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateTodoIsInBookmark(
                Todo(id, title, isCompleted, date, isInBookmark)
            )
        }
    }

    //Get uncompleted todos by their date
    fun getUncompletedTodosByDate(date: String): Flow<List<Todo>> {
        return todoDao.getUncompletedTodosByDate(date)
    }

    //Get completed todos by their date
    fun getCompletedTodosByDate(date: String): Flow<List<Todo>> {
        return todoDao.getCompletedTodosByDate(date)
    }
}