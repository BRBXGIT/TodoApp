package com.example.besttodolist.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.besttodolist.R
import com.example.besttodolist.presentation.sign_in.UserData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MainScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {
    val fontForLogo = FontFamily(Font(R.font.protestriot_regular))
    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yy")
    val currentDate = LocalDateTime.now().format(formatter)

    //Main column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff122233))
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .padding(start = 16.dp, end = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Todo List",
                fontWeight = FontWeight.Bold,
                fontFamily = fontForLogo,
                fontSize = 20.sp,
                color = Color(0xff6699CC),
                modifier = Modifier
                    .clickable { mainScreenViewModel.upsertTodo("Yes yes yes", "27-02-24") }
            )
            if(userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable {
                            onSignOut()
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 18.sp)) {
                        append("Today  ")
                    }
                    withStyle(SpanStyle(color = Color(0xff808080), fontSize = 17.sp, fontWeight = FontWeight.Thin)) {
                        append(currentDate)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        val unCompletedTodos = mainScreenViewModel.getUncompletedTodosByDate(currentDate).collectAsState(
            initial = emptyList()
        )
        val completedTodos = mainScreenViewModel.getCompletedTodosByDate(currentDate).collectAsState(
            initial = emptyList()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
        ) {
            items(unCompletedTodos.value.sortedByDescending { todo -> todo.isInBookmark }, key = {todo -> todo.id}) { todo ->
                TodoItem(id = todo.id, title = todo.title, date = todo.date, isInBookmark = todo.isInBookmark, isCompleted = todo.isCompleted)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07f)
                .padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = "Completed Today", color = Color(0xfff7f7f7), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(start = 32.dp, end = 32.dp)
        ) {
            items(completedTodos.value,  key = {todo -> todo.id}) { todo ->
                CompletedTodoItem(title = todo.title, date = todo.date, id = todo.id, isInBookmark = todo.isInBookmark, isCompleted = todo.isCompleted)
            }
        }
    }
}
