package com.example.mobilesub.ui.theme.view.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilesub.Constants
import com.example.mobilesub.Constants.LIST_ARG_KEY
import com.example.mobilesub.Constants.LIST_SCREEN
import com.example.mobilesub.data.models.toAction
import com.example.mobilesub.ui.theme.view.MainList
import com.example.mobilesub.ui.theme.view.SharedViewModel

fun NavGraphBuilder.listComposeScreen(navigateToDetailPage: (userId:Int) -> Unit,sharedViewModel: SharedViewModel) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARG_KEY) {
            type = NavType.StringType
        })
    ) {navBackStackEntry ->
        val action =navBackStackEntry.arguments?.getString(LIST_ARG_KEY).toAction()
        
        LaunchedEffect(key1 = action ){
            sharedViewModel.action.value = action

        }


        MainList(
             navigateToDetailPage=navigateToDetailPage,
            sharedViewModel = sharedViewModel)

    }

}