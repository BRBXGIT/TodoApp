package com.example.besttodolist.presentation.main_screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.besttodolist.R
import com.example.besttodolist.presentation.nav_bar.BottomBar
import com.example.besttodolist.presentation.shared_viewModels.SharedVMCalendarMainScreens
import com.example.besttodolist.presentation.sign_in.UserData
import com.google.accompanist.systemuicontroller.SystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    navController: NavHostController,
    systemUiController: SystemUiController,
    sharedVMCalendarMainScreens: SharedVMCalendarMainScreens
) {
    val fontForLogo = FontFamily(Font(R.font.protestriot_regular))

    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
    val date = sharedVMCalendarMainScreens.chosenDate

    //Change system bars colors
    SideEffect {
        systemUiController.setStatusBarColor(Color(0xff162232))
        systemUiController.setNavigationBarColor(Color(0xff223148))
    }

    Scaffold(
        bottomBar = { BottomBar(
            navController,
            sharedVMCalendarMainScreens = sharedVMCalendarMainScreens
        ) },
        floatingActionButtonPosition = FabPosition.Center
    ) {

        //Main column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff162232))
        ) {

            Spacer(modifier = Modifier.height(15.dp))

            //Up row with Label and user's icon
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
                    color = Color(0xff85aff7),
                )

                //Checking if user has profile picture
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

            //Box with date
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                val todayText = if(sharedVMCalendarMainScreens.chosenDate == sharedVMCalendarMainScreens.todayDate) "Today:  " else "Date:  "
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Color(0xfff7f7f7), fontSize = 18.sp)) {
                            append(todayText)
                        }
                        withStyle(SpanStyle(color = Color(0xff787b7b), fontSize = 17.sp, fontWeight = FontWeight.Thin)) {
                            append(date)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            //All data from db
            val unCompletedTodos = mainScreenViewModel.getUncompletedTodosByDate(date).collectAsState(
                initial = emptyList()
            ).value
            val completedTodos = mainScreenViewModel.getCompletedTodosByDate(date).collectAsState(
                initial = emptyList()
            ).value

            //LazyColumn with uncompleted todos
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp)
            ) {
                items(unCompletedTodos.sortedByDescending { todo -> todo.isInBookmark }, key = {todo -> todo.id}) { todo ->
                    TodoItem(id = todo.id, title = todo.title, date = todo.date, isInBookmark = todo.isInBookmark, isCompleted = todo.isCompleted)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            //Box with label which separates two lazyColumns
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

            //LazyColumn with completed todos
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.821f)
                    .padding(start = 32.dp, end = 32.dp)
            ) {
                items(completedTodos,  key = {todo -> todo.id}) { todo ->
                    CompletedTodoItem(title = todo.title, date = todo.date, id = todo.id, isInBookmark = todo.isInBookmark, isCompleted = todo.isCompleted)
                }
            }
        }
    }
}
