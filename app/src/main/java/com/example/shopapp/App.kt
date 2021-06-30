package com.example.shopapp

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class App: Application() {

    @Singleton
    @Provides
    fun builder(@ApplicationContext context: Context): String {
        return  "asdf"
    }

}