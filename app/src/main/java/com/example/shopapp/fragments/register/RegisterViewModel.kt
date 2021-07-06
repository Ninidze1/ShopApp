package com.example.shopapp.fragments.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.models.SignUp
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.network.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    private var _registerInfo = MutableLiveData<ResultHandle<SignUp>>()
    val registerInfo: LiveData<ResultHandle<SignUp>> = _registerInfo

    fun register(email: String, fullName: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val result = loginRepository.register(email, fullName, password)
                _registerInfo.postValue(result)
            }
        }
    }
}