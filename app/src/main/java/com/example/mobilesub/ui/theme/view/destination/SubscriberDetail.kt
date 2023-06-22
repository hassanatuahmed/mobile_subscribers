package com.example.mobilesub.ui.theme.view.destination
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilesub.constants.SUBSCRIBER_SCREEN
import com.example.mobilesub.constants.SUB_ARG_KEY
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.ui.theme.view.DetailScreen
import com.example.mobilesub.ui.theme.view.SharedViewModel

fun NavGraphBuilder.detailComposeScreen(sharedViewModel: SharedViewModel,navigateToListScreen:(Action) -> Unit,navController:NavController) {
    composable(
        route = SUBSCRIBER_SCREEN,

        arguments = listOf(navArgument(SUB_ARG_KEY) {
            type = NavType.StringType
            defaultValue = 1
        })
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments
        val id = arguments?.getInt(SUB_ARG_KEY)


//        val userId = navBackStackEntry.arguments!!.getString(SUB_ARG_KEY)
        if (id != null) {
            sharedViewModel.getSelectSubscriber(id)
        }

        val selectedUser by sharedViewModel.selectedUser.collectAsState()
        
        LaunchedEffect(key1 = selectedUser){
            selectedUser?.let { sharedViewModel.updateUserField(subscriber = it) }
        }

        DetailScreen(navigateToListScreen = navigateToListScreen, selectedUser = selectedUser,sharedViewModel)
    }

}