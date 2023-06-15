package com.example.mobilesub.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilesub.data.models.Subscriber

class MyViewModel(application: Application) : ViewModel(){
    private val allUser: LiveData<List<Subscriber>>
    private val repository:SubscriberRepository
    private val myResult: MutableLiveData<List<Subscriber>>


    init {
        val subscriberDb = SubscribersDatabase.getInstance(application)
        val subscriberDao = subscriberDb.subscriberDao()
        repository = SubscriberRepository(subscriberDao)

        allUser = repository.allUser
        myResult = repository.myResults
    }

    fun insertUser(subscriber: Subscriber){
        repository.insertSubscriber(subscriber)
    }



}