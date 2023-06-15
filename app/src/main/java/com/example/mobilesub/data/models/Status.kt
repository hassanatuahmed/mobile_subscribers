package com.example.mobilesub.data.models

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.mobilesub.R

enum class Status(@SuppressLint("SupportAnnotationUsage")  val color: Int) {
    PREPAID(color = R.color.prepaid),
    POSTPAID(color= R.color.postpaid)
}