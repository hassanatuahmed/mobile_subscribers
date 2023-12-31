package com.example.mobilesub.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobilesub.data.SubscribersDatabase
import com.example.mobilesub.data.repository.AuthRepository
import com.example.mobilesub.data.repository.SubscriberRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SubscriberModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context
    ): SubscribersDatabase {

        return Room.databaseBuilder(context,SubscribersDatabase::class.java,
            "subscriber_database").build()
    }

    @Singleton
    @Provides
    fun provideSubscriberDao(database: SubscribersDatabase) = database.subscriberDao()

    @Provides
    @Singleton
    fun providesFirebaseAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }






}