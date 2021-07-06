package com.example.shopapp.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.diffColorCenter(text: Array<String>, res: Array<Int>) {
    val str = text.joinToString(" ")
    val spannable = SpannableString(str)

    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, res[1])),
        text[0].length, text[0].length + text[1].length+1,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannable
}

fun TextView.diffColorEnd(text: Array<String>, res: Array<Int>) {
    val str = text.joinToString(" ")
    val spannable = SpannableString(str)

    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, res[1])),
        text[0].length+1, str.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannable
}

