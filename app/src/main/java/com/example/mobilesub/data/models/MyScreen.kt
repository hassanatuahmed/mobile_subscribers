package com.example.mobilesub.data.models

import com.example.mobilesub.Constants.LIST_SCREEN
import com.example.mobilesub.Constants.SUBSCRIBER_SCREEN

sealed class MyScreen(val route : String) {
    object MainScreen : MyScreen(LIST_SCREEN)
    object DetailScreen : MyScreen(SUBSCRIBER_SCREEN)

    fun withArgs(vararg args: String):String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}