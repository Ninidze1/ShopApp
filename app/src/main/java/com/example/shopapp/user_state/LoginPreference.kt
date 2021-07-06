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

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("myToken", token).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString("myToken", "none").toString()
    }

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("myUserId", userId).apply()
    }

    fun deleteSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


    fun checkSession() = sharedPreferences.getBoolean("isLoggined", false)



}