package com.example.mobilesub.ui.theme.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mobilesub.constants.LIST_SCREEN
import com.example.mobilesub.ui.theme.view.SharedViewModel
import com.example.mobilesub.ui.theme.view.destination.detailComposeScreen
import com.example.mobilesub.ui.theme.view.destination.listComposeScreen

@Composable
fun SetupNavigation(navController: NavHostController,sharedViewModel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController)
    }

    NavHost(navController, LIST_SCREEN) {
        listComposeScreen (screen.subscriber,sharedViewModel)
        detailComposeScreen(screen.list,sharedViewModel)



    }


}