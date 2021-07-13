package com.example.shopapp.network.repository

import com.example.shopapp.models.PostItem
import com.example.shopapp.network.network.ApiService
import com.example.shopapp.network.network.ResultHandle
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: ApiService,
) : PostsRepository {

    override suspend fun getPosts(): ResultHandle<List<PostItem>> {
        return try {
            val response = postService.getPosts()
            if (response.isSuccessful) {
                ResultHandle.success(response.body()!!)
            } else {
                ResultHandle.error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ResultHandle.error(e.message.toString())
        }
    }
}