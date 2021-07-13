package com.example.shopapp.network.repository

import com.example.shopapp.models.PostItem
import com.example.shopapp.network.network.ResultHandle

interface PostsRepository {
    suspend fun getPosts(): ResultHandle<List<PostItem>>
}