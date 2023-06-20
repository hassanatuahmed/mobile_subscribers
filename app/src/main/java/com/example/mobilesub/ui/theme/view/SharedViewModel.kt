package com.example.mobilesub.ui.theme.view

import android.app.Notification
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.compose.runtime.remember
import androidx.compose.runtime.*

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ForeignKey
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.Subscriber
import com.example.mobilesub.data.repository.SubscriberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
//@ActivityRetainedScoped
class SharedViewModel @Inject constructor
    (private val repository: SubscriberRepository) : ViewModel() {


    var emailInput by   mutableStateOf("")
    var nameInput by   mutableStateOf("")
    var phoneInput by   mutableStateOf("")
    var locationInput by   mutableStateOf("")
    var statusInput by  mutableStateOf("")
    var dobInput by   mutableStateOf("")

    private val _data = MutableStateFlow<List<Subscriber>>(emptyList())
    val data: StateFlow<List<Subscriber>> get() = _data



        fun getAllUserFlow(){
            viewModelScope.launch {
                repository.getAllUsersFlow.collect{
                    _data.value = it
                }
            }
        }
        val allUsers:LiveData<List<Subscriber>> =repository.getAllUsers

    fun addUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = Subscriber(
                email = emailInput,
                phoneNumber = phoneInput,
                status = statusInput,
                location = locationInput,
                dateOfBirth = dobInput,
                subscriberName = nameInput


            )
            repository.addUser(subscriber = user)
        }

    }

    fun handleDatabaseActions(action: Action){
        when(action){
            Action.ADD ->{
                addUser()
            }
            Action.UPDATE ->{

            }

            else -> {}
        }
        Action.NO_ACTION


    }

    fun updateUser(subscriber: Subscriber){
        if(subscriber != null){
            emailInput = subscriber.email
            nameInput = subscriber.subscriberName
            phoneInput = subscriber.phoneNumber
            locationInput = subscriber.location
            statusInput = subscriber.status
            dobInput = subscriber.dateOfBirth
        }
    }
}