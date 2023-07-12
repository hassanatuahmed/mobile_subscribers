package com.example.mobilesub.ui.theme.view.destination
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilesub.Constants.SUBSCRIBER_SCREEN
import com.example.mobilesub.Constants.SUB_ARG_KEY
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.ui.theme.view.DetailScreen
import com.example.mobilesub.ui.theme.view.SharedViewModel

fun NavGraphBuilder.detailComposeScreen(sharedViewModel: SharedViewModel,
                                        navigateToListScreen:(Action) -> Unit,
                                        navController: NavController) {
    composable(
        route = SUBSCRIBER_SCREEN,

        arguments = listOf(
            navArgument(SUB_ARG_KEY) {
            type = NavType.IntType

        })
    ) { navBackStackEntry ->
        val userId = navBackStackEntry.arguments?.getInt(SUB_ARG_KEY)

        if (userId != null) {
            sharedViewModel.getSelectSubscriber(userId)
        }

        val selectedUser by sharedViewModel.selectedUser.collectAsState()

        
        LaunchedEffect(key1 = selectedUser){

            selectedUser?.let { sharedViewModel.updateUserField(it) }


        }

        DetailScreen(navigateToListScreen = navigateToListScreen, selectedUser = selectedUser, navController = navController,sharedViewModel)
    }

}