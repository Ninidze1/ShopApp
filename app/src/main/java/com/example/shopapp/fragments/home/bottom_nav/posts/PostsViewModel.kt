package com.example.shopapp.fragments.home.bottom_nav.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.models.PostItem
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.network.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val postRepository: PostsRepository) : ViewModel() {

    private var _posts = MutableLiveData<ResultHandle<List<PostItem>>>()
    val post: LiveData<ResultHandle<List<PostItem>>> = _posts

    private var _loading = MutableLiveData<Boolean>().apply {
        true
    }
    var loading: LiveData<Boolean> = _loading

    fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val result = postRepository.getPosts()
                _posts.postValue(result)
                _loading.postValue(false)
            }


        }
    }

}