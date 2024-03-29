package com.example.besttodolist.presentation.nav_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.besttodolist.R
import com.example.besttodolist.presentation.main_screen.MainScreenViewModel
import com.example.besttodolist.presentation.shared_viewModels.SharedVMCalendarMainScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBar(
    navController: NavHostController,
    sharedVMCalendarMainScreens: SharedVMCalendarMainScreens
) {

    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()

    //Modal sheet vars
    var openAddTodoSheet by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val addTodoSheetState = rememberModalBottomSheetState()

    val currentDestination = navController.currentDestination?.route.toString()

    if(openAddTodoSheet) {
        //Modal sheet for creating to_do
        ModalBottomSheet(
            containerColor = Color(0xff162232),
            contentColor = Color(0xfff7f7f7),
            onDismissRequest = {
                scope.launch { addTodoSheetState.hide() }
                openAddTodoSheet = false
            },
            sheetState = addTodoSheetState,
            modifier = Modifier
                .height(200.dp)
        ) {
            //Column with text field and buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 32.dp, end = 32.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                //TextField for to_do title
                var todoTitle by rememberSaveable { mutableStateOf("") }
                var isInBookmark by rememberSaveable { mutableStateOf(false) }

                OutlinedTextField(
                    value = todoTitle,
                    onValueChange = { todoTitle = it },
                    label = { Text(text = "Title") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = Color(0xfff7f7f7),
                        focusedLabelColor = Color(0xff85aff7),
                        unfocusedBorderColor = Color(0xfff7f7f7),
                        focusedBorderColor = Color(0xff85aff7)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                //Row with add and bookmark buttons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    //Add to_do button
                    Button(
                        onClick = {
                            mainScreenViewModel.upsertTodo(todoTitle, sharedVMCalendarMainScreens.chosenDate, isInBookmark)
                            scope.launch {
                                addTodoSheetState.hide()
                            }
                            openAddTodoSheet = false
                            isInBookmark = false
                            todoTitle = ""
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(42.dp)
                            .width(150.dp)
                            .shadow(4.dp, RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff85aff7),
                            contentColor = Color(0xfff7f7f7)
                        ),
                    ) {
                        Text(text = "Add Todo", fontSize = 15.sp)
                    }

                    //Bookmark button
                    Button(
                        contentPadding = PaddingValues(0.dp),
                        onClick = {  },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(42.dp)
                            .width(42.dp)
                            .shadow(4.dp, RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff85aff7),
                            contentColor = Color(0xfff7f7f7)
                        ),
                    ) {
                        //Bookmark icon
                        if(isInBookmark) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bookmark_filled),
                                tint = Color(0xfff7f7f7),
                                contentDescription = "Bookmark icon",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        isInBookmark = !isInBookmark
                                    }
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bookmark),
                                tint = Color(0xfff7f7f7),
                                contentDescription = "Bookmark icon",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        isInBookmark = !isInBookmark
                                    }
                            )
                        }
                    }
                }
            }
        }
    }

    //Bottom app bar
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
            //Home icon
            if(currentDestination == "main_screen") {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home_filled),
                    contentDescription = "Home icon",
                    tint = Color(0xff85aff7),
                    modifier = Modifier
                        .size(28.dp)
                        .noRippleClickable {
                            navController.navigate("main_screen")
                        }
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home_outlined),
                    contentDescription = "Home icon",
                    tint = Color(0xff8f8f8f),
                    modifier = Modifier
                        .size(28.dp)
                        .noRippleClickable {
                            navController.navigate("main_screen")
                        }
                )
            }

            //Icon for open modal sheet
            FloatingActionButton(
                onClick = {
                    openAddTodoSheet = true
                },
                contentColor = Color(0xfff7f7f7),
                shape = RoundedCornerShape(10.dp),
                containerColor = Color(0xff85aff7),
                modifier = Modifier
                    .size(40.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_plus), contentDescription = "Plus icon")
            }

            //Calendar icon
            if(currentDestination == "calendar_screen") {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_filled),
                    contentDescription = "Calendar icon",
                    tint = Color(0xff85aff7),
                    modifier = Modifier
                        .size(28.dp)
                        .noRippleClickable {
                            navController.navigate("calendar_screen")
                        }
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_outlined),
                    contentDescription = "Calendar icon",
                    tint = Color(0xff8f8f8f),
                    modifier = Modifier
                        .size(28.dp)
                        .noRippleClickable {
                            navController.navigate("calendar_screen")
                        }
                )
            }
        }
    }
}

//Modifier extension for clicking without ripple
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}