package com.example.mobilesub.ui.theme.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MyFloatingActionButton(navigateToDetailPage: (userId: Int) -> Unit) {


    FloatingActionButton(
        onClick = { navigateToDetailPage(-1) },
        shape = CircleShape,
        containerColor = Color.Blue.copy(alpha = 0.9f),


        modifier = Modifier,

        contentColor = Color.White
    ) {
        Icon(
            Icons.Filled.Add,

            contentDescription = ""
        )

    }
}