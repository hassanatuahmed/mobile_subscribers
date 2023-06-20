package com.example.mobilesub.ui.theme.view.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilesub.constants
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.ui.theme.view.SharedViewModel
import com.example.mobilesub.ui.theme.view.SubscribersDetail

fun NavGraphBuilder.detailComposeScreen(navigateListPage: (Action) -> Unit,sharedViewModel: SharedViewModel) {
    composable(
        route = constants.subscriber_screen,
        arguments = listOf(navArgument(constants.SUB_ARG_KEY) {
            type = NavType.StringType
        })
    ) {
        SubscribersDetail(sharedViewModel)

    }

}