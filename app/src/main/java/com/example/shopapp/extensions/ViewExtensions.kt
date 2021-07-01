package com.example.shopapp.extensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.hideIf(visibility: Boolean) {
    if (visibility) {
        hide()
    } else {
        show()
    }
}
