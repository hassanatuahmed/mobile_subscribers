package com.example.mobilesub.data.models

enum class Action {
    ADD,
    UPDATE,
    NO_ACTION
}

fun String?.toAction(): Action{
    return when{
        this == "ADD" ->{
            Action.ADD
        }
        this == "UPDATE" ->{
            Action.UPDATE
        }else ->{
            Action.NO_ACTION
        }
    }
}