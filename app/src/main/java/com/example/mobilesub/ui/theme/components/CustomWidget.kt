package com.example.mobilesub.ui.theme.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesub.R
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.Subscriber
import kotlin.random.Random



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    title: String
) {


    TopAppBar(
        actions = { TopBarComposable(onSearchClicked = {}) },
        title = { Text(title, color = Color.White) },


        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.button_color)
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBarNew(
     NavigateToListScreen: (Action) -> Unit
) {
    TopAppBar(

//        actions = { AddAction(onAddClicked = NavigateToListScreen) },
        title = { Text("Add Subscriber", color = Color.White) },

        navigationIcon = { BackAction(onBackClicked = NavigateToListScreen) },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.button_color)
        )
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBarEx(
    NavigateToListScreen: (Action) -> Unit,
    selectedUser: Subscriber
) {


    TopAppBar(

        actions = {
            CloseAction(NavigateToListScreen)
//            UpdateAction(onUpdateClicked = NavigateToListScreen)
        },
        title = { Text("Subscriber Detail", overflow = TextOverflow.Ellipsis, color = Color.White) },

        navigationIcon = { BackAction(onBackClicked =NavigateToListScreen ) },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.button_color)
        )
    )

}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(
        onClick = { onCloseClicked(Action.NO_ACTION) },
        colors = IconButtonDefaults.filledIconButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.button_color)
        )
    ) {
        Icon(Icons.Filled.Close, contentDescription = "")

    }

}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(
        onClick = { onUpdateClicked(Action.UPDATE) },
        colors = IconButtonDefaults.filledIconButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.button_color)
        )
    ) {
        Icon(Icons.Filled.Check, contentDescription = "")

    }

}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(
        onClick = { onBackClicked(Action.NO_ACTION) },
        colors = IconButtonDefaults.filledIconButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.button_color)
        )
    ) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "")

    }

}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(
        onClick = { onAddClicked(Action.ADD) },
        colors = IconButtonDefaults.filledIconButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.button_color)
        )
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")

    }

}

@Composable
fun TopBarComposable(onSearchClicked: () -> Unit) {

    SearchComposable(onSearchClicked)
}

@Composable
fun SearchComposable(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",

            tint = MaterialTheme.colorScheme.primary
        )

    }

}


fun generateRandomColor(): Color {
    val random = Random.Default
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}




@Composable

fun Circle(initial: String) {
    val randomColor = generateRandomColor()

    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(randomColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = initial,
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.White
            )
        )
    }
}









