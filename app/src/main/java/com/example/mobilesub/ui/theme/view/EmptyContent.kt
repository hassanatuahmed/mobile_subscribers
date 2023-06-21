package com.example.mobilesub.ui.theme.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R

@Composable
fun EmptyContent(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primary),

        verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(painter = painterResource(id = R.drawable.sad_face),
            contentDescription = "",tint= Color.Blue,
            modifier = Modifier.size(100.dp)
        )

        Text(text="No Record Found", fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        
    }
}