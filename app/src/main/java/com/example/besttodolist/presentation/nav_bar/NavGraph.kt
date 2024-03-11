package com.example.besttodolist.presentation.nav_bar

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.besttodolist.presentation.calendar_screen.CalendarScreen
import com.example.besttodolist.presentation.main_screen.MainScreen
import com.example.besttodolist.presentation.shared_viewModels.SharedVMCalendarMainScreens
import com.example.besttodolist.presentation.sign_in.GoogleAuthUiClient
import com.example.besttodolist.presentation.sign_in.SignInScreen
import com.example.besttodolist.presentation.sign_in.SignInViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient
) {

    val sharedVMCalendarMainScreens = viewModel<SharedVMCalendarMainScreens>()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    //Changing start destination if user signed in
    var startDestination = "sign_in"
    if(googleAuthUiClient.getSignedInUser() != null) {
        startDestination = "main_screen"
    }

    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("sign_in") {
            val signInViewModel = viewModel<SignInViewModel>()
            val state by signInViewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == ComponentActivity.RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            signInViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            //Signing in user
            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if(state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("main_screen")
                    signInViewModel.resetState()
                }
            }

            SignInScreen(
                state = state,
                onSignInClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                systemUiController = systemUiController
            )
        }

        composable(route = "main_screen") {
            MainScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    scope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Sign out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("sign_in")
                    }
                },
                navController = navController,
                systemUiController = systemUiController,
                sharedVMCalendarMainScreens = sharedVMCalendarMainScreens
            )
        }

        composable(route = "calendar_screen") {
            CalendarScreen(
                navController = navController,
                sharedVMCalendarMainScreens = sharedVMCalendarMainScreens
            )
        }
    }
}