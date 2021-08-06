package com.rasyidin.serieshunt.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.toOnlyYearFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.toDateMonthYearFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM y", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}