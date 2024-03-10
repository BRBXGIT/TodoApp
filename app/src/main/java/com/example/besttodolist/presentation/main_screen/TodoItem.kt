package com.example.besttodolist.presentation.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.besttodolist.R

//Composable function for uncompleted to_do
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(
    id: Int,
    title: String,
    date: String,
    isInBookmark: Boolean,
    isCompleted: Boolean,
) {
    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()

    Spacer(modifier = Modifier.height(6.dp))

    //Main row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .shadow(8.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xff223148))
            .animateItemPlacement()
    ) {

        //Row with complete button, date and title
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Button which presented like a box to update to_do to completed
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clip(CircleShape)
                    .border(width = 4.dp, shape = CircleShape, color = Color(0xff88b7fe))
                    .clickable {
                        mainScreenViewModel.updateTodoIsCompleted(
                            id = id,
                            title = title,
                            date = date,
                            isCompleted = !isCompleted,
                            isInBookmark = isInBookmark
                        )
                    } //Updating to_do to completed
            )

            //Column with title and date
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = title, fontSize = 16.sp, color = Color(0xfff7f7f7), maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = date, fontSize = 12.sp, color = Color(0xffc17015))
            }
        }


        //Box with bookmark icon
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            //Updating bookmark icon with isInBookmark parameter
            if(isInBookmark) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark_filled),
                    contentDescription = "Bookmark icon",
                    tint = Color(0xfff7f7f7),
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            mainScreenViewModel.updateTodoIsInBookmark(
                                id = id,
                                title = title,
                                date = date,
                                isCompleted = isCompleted,
                                isInBookmark = !isInBookmark
                            )
                        }
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "Bookmark icon",
                    tint = Color(0xfff7f7f7),
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            mainScreenViewModel.updateTodoIsInBookmark(
                                id = id,
                                title = title,
                                date = date,
                                isCompleted = isCompleted,
                                isInBookmark = !isInBookmark
                            )
                        }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
}

//Composable function for uncompleted to_do
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.CompletedTodoItem(
    title: String,
    date: String,
    id: Int,
    isInBookmark: Boolean,
    isCompleted: Boolean,
) {
    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()

    Spacer(modifier = Modifier.height(6.dp))

    //Main row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0x90223148))
            .animateItemPlacement()
    ) {

        //Row with uncomplete button(presented with box), title and date
        Row(
            modifier = Modifier
                .fillMaxWidth(0.58f)
                .fillMaxHeight()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clip(CircleShape)
                    .background(Color(0x904a638c))
                    .clickable {
                        mainScreenViewModel.updateTodoIsCompleted(
                            id = id,
                            title = title,
                            date = date,
                            isCompleted = !isCompleted,
                            isInBookmark = isInBookmark
                        )
                    } //Updating to_do to unCompleted
            )

            //Column with title and date
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = title, fontSize = 16.sp, color = Color(0x9049608a), maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = date, fontSize = 12.sp, color = Color(0x9049608a))
            }
        }

        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp)
        ) {
            //Row with completed text and icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "Completed", color = Color(0xfff7f7f7), fontSize = 14.sp)

                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Completed icon",
                    tint = Color(0xff86aef5),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
}