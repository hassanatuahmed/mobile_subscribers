package com.example.mobilesub.ui.theme.view.destination

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.example.mobilesub.ui.theme.signin.signup.SignupPage
import com.example.mobilesub.ui.theme.view.SharedViewModel

fun NavGraphBuilder.signUpComposeScreen(sharedViewModel: SharedViewModel,navController: NavController){

    composable(
        route = "sign_up"
    ){
//        val state by viewModel.state.collectAsState()

        SignupPage(sharedViewModel,navController)




    }
}