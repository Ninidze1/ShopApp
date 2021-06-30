package com.example.shopapp.network.repository

import com.example.shopapp.models.User
import com.example.shopapp.network.network.ApiService
import com.example.shopapp.network.network.ResultHandle
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : LoginRepository {
    override suspend fun logIn(email: String, password: String): ResultHandle<User> {


        return try {
            val response = apiService.login(email, password)
            if (response.isSuccessful) {
                ResultHandle.success(response.body()?.data)
            } else {
                ResultHandle.error(response.errorBody()?.toString())
            }
        } catch (e:Exception) {
            ResultHandle.error(e.message)
        }

    }
}