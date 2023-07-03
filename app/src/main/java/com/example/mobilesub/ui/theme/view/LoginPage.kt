package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage() {


    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Sign In", color = Color.White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(
                    id = R.color.button_color
                )
            ))
    }) {
        Column(modifier = Modifier.padding(top = 70.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)) {
            MyTextField(value = "", onValueChange = {})
            OutlinedTextField(
                value = "",

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .height(60.dp),
                onValueChange = {},
                shape = RoundedCornerShape(10.dp)
            )
            MyButton(onClick = {})
            Row() {
              Text(text = "Forgot password?")
              Text(text = "|")
              Text(text = "No account? Sign up")
            }
            Button(
                onClick = {  },

                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_color) // Set the desired background color here
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)

                    .height(60.dp)
            ) {
                Row() {
                    Icon( Icons.Filled.Email, contentDescription ="" )
                    Text(text = "Sign up with Google")
                }


            }

        }
    }
}