package com.example.shopapp.fragments.home.bottom_nav.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.models.Item
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.network.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    private var _posts = MutableLiveData<ResultHandle<MutableList<Item>>>()
    val post: LiveData<ResultHandle<MutableList<Item>>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val result = loginRepository.getPosts()
                _posts.postValue(result)
            }
        }
    }

}