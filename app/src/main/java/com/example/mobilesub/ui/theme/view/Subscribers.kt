package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R

enum class MyScreen(@StringRes val title:Int){
    Start(title = R.string.subscribers),
    SubscriberDetail(title = R.string.sub_detail)

}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainList(modifier: Modifier = Modifier,navigateToDetailPage: (Int) -> Unit,sharedViewModel: SharedViewModel){
    val itemList = mutableListOf(
        "Happy", "Happy", "Happy", "Happy",
        "Happy", "Happy", "Happy", "Happy", "Happy"
    )
    Scaffold(floatingActionButton =
    { MyFloatingActionButton (navigateToDetailPage=navigateToDetailPage) },
        topBar = { MyAppBar()}) {
        LazyColumn(modifier = Modifier.padding(top =60.dp )) {
            items(itemList) { item ->
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
                                Text(text = "$item")
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = "")

                                Spacer(modifier = Modifier.weight(1f))

                                Text(text = "$item")
                            }
                            Spacer(
                                Modifier
                                    .weight(4f)
                                    .fillMaxHeight()
                                    .background(Color.Green)
                            )

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = modifier.align(Alignment.Bottom)
                            ) {
                                Text(text = "PostPaid")


                            }
                        }
                    }
                }
            }
        }



    }
}


@Composable
fun MyCircle() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = Modifier
            .clip(CircleShape)
            .height(60.dp),
    )

}