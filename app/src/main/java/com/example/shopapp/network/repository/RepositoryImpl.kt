package com.example.shopapp.network.repository

import com.example.shopapp.models.Item
import com.example.shopapp.models.SignUp
import com.example.shopapp.models.SignIn
import com.example.shopapp.network.network.ApiService
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.user_state.LoginPreference
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService, private val sharedPreferences: LoginPreference) : LoginRepository {
    override suspend fun logIn(email: String, password: String): ResultHandle<SignIn> {


        return try {
            val response = apiService.login(email, password)
            if (response.isSuccessful) {
                ResultHandle.success(response.body()!!.data)
            } else {
                ResultHandle.error(response.body()!!.error)
            }
        }catch (e: Exception){
            ResultHandle.error(e.message.toString())
        }
    }

    override suspend fun register(email: String, fullName: String, password: String): ResultHandle<SignUp> {
        return try {
            val response = apiService.register(email, fullName, password)
            if (response.isSuccessful) {
                ResultHandle.success(response.body()?.data)
            } else {
                ResultHandle.error(response.errorBody()?.toString())
            }
        } catch (e:Exception) {
            ResultHandle.error(e.message)
        }
    }

    override suspend fun getPosts(): ResultHandle<MutableList<Item>> {
        return try {
            val response = apiService.getPosts()
            if (response.isSuccessful) {
                ResultHandle.success(response.body()?.data)
            } else {
                ResultHandle.error(response.errorBody()?.toString())
            }
        } catch (e: Exception) {
            ResultHandle.error(e.message)
        }
    }
}