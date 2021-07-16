package com.example.shopapp

import android.app.Application
import android.util.Log.d
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                d("firebase", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
//            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
//            Log.d("firebase", msg)

        })
    }


}