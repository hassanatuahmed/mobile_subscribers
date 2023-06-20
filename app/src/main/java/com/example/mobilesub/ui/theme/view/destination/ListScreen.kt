package com.example.mobilesub.ui.theme.view.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilesub.constants
import com.example.mobilesub.ui.theme.view.MainList
import com.example.mobilesub.ui.theme.view.SharedViewModel

fun NavGraphBuilder.listComposeScreen(navigateToDetailPage: (userId:Int) -> Unit,sharedViewModel: SharedViewModel) {
    composable(
        route = constants.LIST_SCREEN,
        arguments = listOf(navArgument(constants.LIST_ARG_KEY) {
            type = NavType.StringType
        })
    ) {

        MainList(navigateToDetailPage=navigateToDetailPage, sharedViewModel = sharedViewModel)

    }

}