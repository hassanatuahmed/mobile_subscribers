package com.example.mobilesub.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilesub.data.models.Subscriber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberRepository(private val subscriberDao: SubscriberDao) {
    val myResults = MutableLiveData<List<Subscriber>>()
    val allUser: LiveData<List<Subscriber>> = subscriberDao.getAllUser()


    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertSubscriber(newUser: Subscriber){
       coroutineScope.launch(Dispatchers.IO){
           subscriberDao.insertSubscriber(newUser)
       }

    }
//
//    fun findUser(name:String){
//        coroutineScope.launch(Dispatchers.Main){
//            myResults.value = asyncFind(name).await()
//        }
//
//    }


}