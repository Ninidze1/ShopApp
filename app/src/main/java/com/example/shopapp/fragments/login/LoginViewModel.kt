package com.example.shopapp.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.models.PersonInfo
import com.example.shopapp.models.SignIn
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.network.repository.LoginRepository
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository, private val loginPreference: LoginPreference) : ViewModel() {


    private var _profileInfo = MutableLiveData<ResultHandle<PersonInfo>>()
    val profileInfo: LiveData<ResultHandle<PersonInfo>> = _profileInfo

    private var _loginInfo = MutableLiveData<ResultHandle<SignIn>>()
    val loginInfo: LiveData<ResultHandle<SignIn>> = _loginInfo

    fun login(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val result = loginRepository.logIn(email, password)
                _loginInfo.postValue(result)
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = loginRepository.profileStatus(loginPreference.getUserId())
                _profileInfo.postValue(result)
            }
        }
    }

    fun activateSession(status: Boolean) {
        loginPreference.saveSession(status)
    }

}