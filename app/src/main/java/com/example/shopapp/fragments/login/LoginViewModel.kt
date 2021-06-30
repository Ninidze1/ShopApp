package com.example.shopapp.fragments.login

import android.util.Log.d
import androidx.lifecycle.ViewModel
import com.example.shopapp.network.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    suspend fun login(email: String, password: String) {
        loginRepository.logIn(email, password)
    }

}