package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import androidx.annotation.StringRes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobilesub.R


enum class MyScreen(@StringRes val title:Int){
    Start(title = R.string.subscribers),
    SubscriberDetail(title = R.string.sub_detail)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMainScreen(){
    val navController = rememberNavController()
MainApp(navController = navController,
    navigateUp = {navController.navigateUp()},

    onClick = {navController.navigate(MyScreen.SubscriberDetail.name)})

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api

@Composable
fun MainApp(navController: NavHostController,
            onClick: () -> Unit,
            navigateUp: () -> Unit,) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyScreen.valueOf(
        backStackEntry ?.destination?.route ?: MyScreen.Start.name
    )
    Scaffold(

        modifier = Modifier

            .safeDrawingPadding(),

        topBar = {

            TopAppBar(
                title = { Text(stringResource(id = currentScreen.title), color = Color.White) },
                navigationIcon = {

                      IconButton(onClick = {navigateUp},
                          colors = IconButtonDefaults.filledIconButtonColors(
                              contentColor = colorResource(id = R.color.white),
                              containerColor = colorResource(id = R.color.button_color)
                          )) {
                          Icon(Icons.Filled.ArrowBack, contentDescription = "")

                      }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(R.color.button_color)
                )
            )
        },

        )
    {innerPadding ->

        NavHost(navController = navController,
            startDestination = MyScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = MyScreen.Start.name){
                MainList(onClick=onClick)
            }
            composable(route = MyScreen.SubscriberDetail.name){
                SubscribersDetail()
            }

        }


    }
}




@Composable
fun MyFloatingActionButton(onClick: () -> Unit) {


    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,

//        containerColor = FloatingActionButtonDefaults.containerColor,

        modifier = Modifier,
        contentColor = Color.White
    ) {
        Icon(
            Icons.Filled.Add,
            ""
        )

    }
}










