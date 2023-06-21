package com.example.mobilesub.ui.theme.view.nav

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mobilesub.constants.LIST_SCREEN
import com.example.mobilesub.constants.SUBSCRIBER_SCREEN
import com.example.mobilesub.data.models.Action

class Screens(navController: NavHostController) {
    val list:(Action) -> Unit = {action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){
                inclusive=true
            }
        }
    }
    val subscriber:(Int) -> Unit = {
        userId -> navController.navigate(SUBSCRIBER_SCREEN)
    }
}



