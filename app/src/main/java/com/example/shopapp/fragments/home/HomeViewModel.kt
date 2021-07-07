package com.example.shopapp.fragments.home

import androidx.lifecycle.ViewModel
import com.example.shopapp.network.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {


}