package com.example.mobilesub.ui.theme.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import com.example.mobilesub.ui.theme.view.SharedViewModel
import com.example.mobilesub.ui.theme.view.destination.detailComposeScreen
import com.example.mobilesub.ui.theme.view.destination.listComposeScreen
import com.example.mobilesub.ui.theme.view.destination.loginComposeScreen
import com.example.mobilesub.ui.theme.view.destination.signUpComposeScreen

@Composable
fun SetupNavigation(navController: NavHostController,
                    sharedViewModel: SharedViewModel,

) {
    val screen = remember(navController) {
        Screens(navController)
    }




    NavHost(navController, "sign_in") {


        detailComposeScreen(sharedViewModel,screen.list,navController)

        listComposeScreen (screen.details,sharedViewModel)

        loginComposeScreen(sharedViewModel,navController)

        signUpComposeScreen(sharedViewModel, navController = navController)

    }


}