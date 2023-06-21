package com.example.mobilesub.ui.theme.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.Subscriber
import kotlin.random.Random

@Composable
fun MyFloatingActionButton(navigateToDetailPage: (userId: Int) -> Unit) {


    FloatingActionButton(
        onClick = { navigateToDetailPage(-1) },
//        onClick = {navigateToDetailPage},
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

        actions = { AddAction(onAddClicked = NavigateToListScreen) },
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
            UpdateAction(onUpdateClicked = NavigateToListScreen)
        },
        title = { Text(selectedUser.subscriberName, overflow = TextOverflow.Ellipsis, color = Color.White) },

        navigationIcon = { BackAction(onBackClicked =NavigateToListScreen )},

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(subscriber: Subscriber, navigateToDetailPage: (userId: Int) -> Unit) {
    Surface(shadowElevation = 2.dp, onClick = { navigateToDetailPage(subscriber.id) }) {
        Card(

            modifier = Modifier
                .fillMaxWidth()

                .padding(20.dp, 10.dp, 20.dp, 10.dp),

            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(30.dp),

                ) {
                MyCircle()
                Row() {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = subscriber.subscriberName)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "")

                        Spacer(modifier = Modifier.weight(1f))

                        Text(text = subscriber.phoneNumber)
                    }
                    Spacer(
                        Modifier
                            .weight(4f)
                            .fillMaxHeight()
                            .background(Color.Green)
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.Bottom)
//                        modifier = Modifier.modifier.align(Alignment.Bottom)
                    ) {
                        Text(text = subscriber.status)


                    }
                }
            }
        }

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
fun MyCircle() {
    val randomColor = generateRandomColor()
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null,

        modifier = Modifier
            .clip(CircleShape)
            .background(randomColor)
            .height(60.dp),
    )

}

@Composable
fun Cc() {
    val randomColor = generateRandomColor()

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "H")
        Canvas(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
        ) {
            drawCircle(color = randomColor)

        }


    }
}










