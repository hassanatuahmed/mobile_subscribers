package com.example.mobilesub.ui.theme.signin.login

import com.google.firebase.auth.AuthResult

data class GoogleSignState(
    val isLoading: Boolean = false,
    val isSuccess: AuthResult? = null,
    val isError: String? = ""
)
