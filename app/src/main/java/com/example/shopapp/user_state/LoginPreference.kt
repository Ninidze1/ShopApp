package com.example.shopapp.user_state

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginPreference @Inject constructor(@ApplicationContext private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("session", Context.MODE_PRIVATE)
    }

    fun saveSession(session: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggined", session).apply()
    }

    fun checkSession() = sharedPreferences.getBoolean("isLoggined", false)

}