package com.example.mobilesub.ui.theme.view.destination

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobilesub.ui.theme.signin.login.LoginPage
import com.example.mobilesub.ui.theme.view.SharedViewModel

fun NavGraphBuilder.loginComposeScreen(sharedViewModel: SharedViewModel,navController: NavController){

    composable(
        route = "sign_in"
    ){

        LoginPage(navController = navController,sharedViewModel)




    }
}