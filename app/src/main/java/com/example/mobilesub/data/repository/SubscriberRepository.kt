package com.example.mobilesub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilesub.data.SubscriberDao
import com.example.mobilesub.data.models.Subscriber
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class SubscriberRepository @Inject constructor(private val subscriberDao: SubscriberDao) {
    val getAllUsers: LiveData<List<Subscriber>> = subscriberDao.getAllUser()

    val getAllUsersFlow: Flow<List<Subscriber>> = subscriberDao.getAllUserFlow()

    fun getUser(id:Int) : Flow<Subscriber> {
        return subscriberDao.getSubscriber(id);
    }

    suspend fun addUser(subscriber: Subscriber){
        subscriberDao.insertSubscriber(subscriber)
    }








}