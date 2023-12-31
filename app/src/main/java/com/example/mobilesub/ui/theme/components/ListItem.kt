package com.example.mobilesub.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R
import com.example.mobilesub.data.models.Subscriber
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(subscriber: Subscriber, navigateToDetailPage: (userId: UUID) -> Unit, modifier: Modifier) {

    var myColor = if (subscriber.status =="Postpaid") {
        ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.postpaid) // Set the desired background color here
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.prepaid) // Set the desired background color here
        )
    }

    var textColor = if (subscriber.status =="Postpaid") {
       colorResource(id = R.color.postpaid_text)
    } else {
        colorResource(id = R.color.prepaid_text)

    }
    Surface(shadowElevation = 2.dp,
        onClick = { navigateToDetailPage(subscriber.id) }) {
        Card(

            modifier = modifier
                .fillMaxWidth()

                .padding(20.dp, 10.dp, 20.dp, 10.dp),

            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(30.dp),

                ) {
                Circle(initial = subscriber.subscriberName.take(1).uppercase())
                Row() {
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = subscriber.subscriberName)
                        Spacer(modifier = modifier.weight(1f))
                        Text(text = "")

                        Spacer(modifier = modifier.weight(1f))

                        Text(text = subscriber.phoneNumber)
                    }
                    Spacer(
                        modifier
                            .weight(4f)
                            .fillMaxHeight()
                            .background(Color.Green)
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier.align(Alignment.Bottom),
                        colors = myColor
//                        modifier = Modifier.modifier.align(Alignment.Bottom)
                    ) {
                        Text(text = subscriber.status, color = textColor)


                    }
                }
            }
        }

    }
}