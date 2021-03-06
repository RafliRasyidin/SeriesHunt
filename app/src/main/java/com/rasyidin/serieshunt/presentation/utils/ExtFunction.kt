package com.rasyidin.serieshunt.presentation.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.toOnlyYearFormat(): String {
    return if (this.isEmpty()) {
        this
    } else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    }

}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.toDateMonthYearFormat(): String {
    return if (this.isEmpty()) this else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM y", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    }

}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}