package com.example.shopapp.fragments.completeInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.models.PersonInfo
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompleteInfoViewModel @Inject constructor(private val userPreference: LoginPreference) : ViewModel() {

    private var _completeProfile = MutableLiveData<ResultHandle<PersonInfo>>()
    val completeProfile: LiveData<ResultHandle<PersonInfo>> = _completeProfile

    private fun completeProfileInfo() {

    }

}