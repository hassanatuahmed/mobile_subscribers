package com.example.mobilesub.ui.theme.view

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.mobilesub.R

@Composable
fun MyFloatingActionButton(navigateToDetailPage: (userId:Int) -> Unit) {


    FloatingActionButton(
        onClick = {navigateToDetailPage(-1)},
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
){


        TopAppBar(
            actions = {TopBarComposable(onSearchClicked = {})},
            title = { Text("Subscriber", color = Color.White) },

//            navigationIcon = {
//
//                IconButton(onClick = {},
//                    colors = IconButtonDefaults.filledIconButtonColors(
//                        contentColor = colorResource(id = R.color.white),
//                        containerColor = colorResource(id = R.color.button_color)
//                    )) {
//                    Icon(Icons.Filled.ArrowBack, contentDescription = "")
//
//                }
//
//            },

            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(R.color.button_color)
            )
        )

}

@Composable
fun TopBarComposable(  onSearchClicked: () ->Unit){

    SearchComposable(onSearchClicked)
}

@Composable
fun SearchComposable(
    onSearchClicked: () ->Unit
){
    IconButton(onClick = { onSearchClicked }) {
        Icon(imageVector = Icons.Filled.Search,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary)
        
    }

}










