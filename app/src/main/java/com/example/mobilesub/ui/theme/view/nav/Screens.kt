package com.example.mobilesub.ui.theme.view.nav

import androidx.navigation.NavHostController
import com.example.mobilesub.Constants.LIST_SCREEN
import com.example.mobilesub.data.models.Action
import java.util.UUID

class Screens(navController: NavHostController) {
    val list:(Action) -> Unit = {action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){
                inclusive=true
            }
        }
    }
    val details :(UUID) -> Unit = { id ->
        navController.navigate("details/$id")
    }

    val loginPage:() -> Unit ={
        navController.navigate("sign_in")
    }

    val SignUpPage:() -> Unit ={
        navController.navigate("sign_up")
    }






}



