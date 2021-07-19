package com.example.shopapp.extensions

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shopapp.R


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hideIf(visibility: Boolean) {
    if (visibility) {
        show()
    } else {
        hide()
    }
}

fun View.goneIf(visibility: Boolean) {
    if (visibility) {
        gone()
    } else {
        show()
    }
}


fun ImageView.loadImg(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_not_found)
        .into(this)
}

fun ImageView.loadUri(uri: Uri?) {
    Glide.with(this.context)
        .load(uri)
        .placeholder(R.drawable.ic_profile_pic)
        .error(R.drawable.ic_not_found)
        .into(this)
}