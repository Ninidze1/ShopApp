package com.example.shopapp.fragments.splash

import androidx.lifecycle.ViewModel
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userPreference: LoginPreference) : ViewModel() {

    fun isActive() = userPreference.checkSession()

}