package com.example.mobilesub.ui.theme.signin.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilesub.Constants.LIST_SCREEN
import com.example.mobilesub.R
import com.example.mobilesub.ui.theme.view.MyButton
import com.example.mobilesub.ui.theme.view.MyTextField
import com.example.mobilesub.ui.theme.view.SharedViewModel
import com.example.mobilesub.ui.theme.view.nav.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(
    navController: NavController,
    sharedViewModel: SharedViewModel) {
    val  googleSignInState =  sharedViewModel.googleState.value
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = sharedViewModel.signInState.collectAsState(initial = null)



    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Sign In", color = Color.White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(
                    id = R.color.button_color
                )
            ))
    }) {
        Column() {
            Box(modifier = Modifier.height(400.dp)) {
                Image(painter = painterResource(id = R.drawable.imglogin),contentDescription = "image", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }
            Column(modifier = Modifier.padding(top = 70.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)) {

                Text(text = "Email")
                MyTextField(value = email, onValueChange = {email = it})
                Text(text = "Password")
                OutlinedTextField(
                    value = password,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(60.dp),
                    onValueChange = {password = it},
                    shape = RoundedCornerShape(10.dp)
                )
                MyButton(onClick = {
                    scope.launch{
                        sharedViewModel.loginUser(email, password)
                    }

                }, text = "Login")
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    if (googleSignInState.isLoading) {
                        CircularProgressIndicator()
                    }

                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                    Text(text = "No account? Sign up", modifier = Modifier.clickable {
                        navController.navigate("sign_up")

                    })
                }

                Button(
                    onClick = {

                    },

                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.button_color) // Set the desired background color here
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)

                        .height(60.dp)
                ) {
                    Text(text = "Sign in with google")

                }







            }
        }

        }


    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                navController.navigate( LIST_SCREEN)

            }
        }
    }


    LaunchedEffect(key1 = googleSignInState.isError) {
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()

            }
        }
    }

    LaunchedEffect(key1 = googleSignInState.isSuccess ){
        scope.launch {
            if (googleSignInState.isSuccess != null){
                Toast.makeText(context,"Sign in Success",Toast.LENGTH_LONG).show()
            }
        }
    }


}