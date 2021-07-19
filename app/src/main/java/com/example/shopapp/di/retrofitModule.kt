package com.example.shopapp.di

import com.example.shopapp.network.network.ApiService
import com.example.shopapp.network.repository.LoginRepository
import com.example.shopapp.network.repository.PostRepositoryImpl
import com.example.shopapp.network.repository.PostsRepository
import com.example.shopapp.network.repository.LoginRepositoryImpl
import com.example.shopapp.user_state.LoginPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class retrofitModule {
    companion object {
        private const val BASE_URL = "https://ktorhighsteaks.herokuapp.com/"
    }

    @Provides
    @Singleton
    fun loginService() = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAuth(appService: ApiService): LoginRepository {
        return LoginRepositoryImpl(appService)
    }

    @Provides
    @Singleton
    fun providePosts(postService: ApiService, userPreference: LoginPreference): PostsRepository {
        return PostRepositoryImpl(postService)
    }
}
