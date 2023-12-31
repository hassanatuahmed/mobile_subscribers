package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.Subscriber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedUser: Subscriber?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            DetailAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedUser = selectedUser
            )
        },

        ) {

       Box(Modifier.padding(20.dp)){
           SubscribersDetail(viewModel = sharedViewModel, navigateToListScreen = navigateToListScreen, navController = navController)
       }
    }


}