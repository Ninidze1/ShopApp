package com.example.shopapp.extensions

import android.app.Dialog
import android.view.Window
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Dialog.showDialog(binding: ViewBinding, title: String, desc: String) {

    window!!.setBackgroundDrawableResource(android.R.color.transparent)
    window!!.requestFeature(Window.FEATURE_NO_TITLE)

    val parameters = window!!.attributes
    parameters.width = WindowManager.LayoutParams.MATCH_PARENT
    parameters.height = WindowManager.LayoutParams.WRAP_CONTENT
    show()
    window!!.setContentView(binding.root)

}
