package com.example.mobilesub.ui.theme.view.destination

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilesub.constants
import com.example.mobilesub.constants.SUBSCRIBER_SCREEN
import com.example.mobilesub.constants.SUB_ARG_KEY
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.MyScreen
import com.example.mobilesub.ui.theme.view.DetailScreen
import com.example.mobilesub.ui.theme.view.SharedViewModel
import com.example.mobilesub.ui.theme.view.SubscribersDetail

fun NavGraphBuilder.detailComposeScreen(sharedViewModel: SharedViewModel,navigateToListScreen:(Action) -> Unit) {
    composable(
        route = SUBSCRIBER_SCREEN,

        arguments = listOf(navArgument(SUB_ARG_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val userId = navBackStackEntry.arguments!!.getInt(SUB_ARG_KEY)
        sharedViewModel.getSelectSubscriber(userId)
        val selectedUser by sharedViewModel.selectedUser.collectAsState()

        DetailScreen(navigateToListScreen = navigateToListScreen, selectedUser = selectedUser,sharedViewModel)
    }

}