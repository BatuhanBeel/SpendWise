package com.example.spendwise.presentation.util

import java.text.SimpleDateFormat
import java.util.Locale

fun convertTimeToString(time: Long): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.US).format(time)
}