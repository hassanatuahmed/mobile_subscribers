package com.example.mobilesub.ui.theme.view

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.RequestState
import com.example.mobilesub.data.models.Subscriber
import com.example.mobilesub.data.repository.SubscriberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
//@ActivityRetainedScoped
class  SharedViewModel @Inject constructor
    (private val repository: SubscriberRepository) : ViewModel() {



    val snackbarHostState =  { SnackbarHostState() }


    var emailInput by   mutableStateOf("")
    var nameInput by   mutableStateOf("")
    var phoneInput by   mutableStateOf("")
    var locationInput by   mutableStateOf("")
    var statusInput by  mutableStateOf("")
    var dobInput by   mutableStateOf("")

    var mExpended by   mutableStateOf(value = false)
    var mTextFieldSize by   mutableStateOf(Size.Zero)

    var isDatePickerVisible by   mutableStateOf(false)

    private val _data = MutableStateFlow<RequestState<List<Subscriber>>>(RequestState.Idle)
    val data: StateFlow<RequestState<List<Subscriber>>> get() = _data

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)



        fun getAllUserFlow(){
            _data.value = RequestState.Loading
            try{
                viewModelScope.launch {
                    repository.getAllUsersFlow.collect{
                        _data.value = RequestState.Success(it)
                    }
                }
            }catch (e: Exception){
                _data.value = RequestState.Error(e)
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

    fun updateUserField(subscriber: Subscriber){


        if(subscriber != null){
            emailInput = subscriber.email
            nameInput = subscriber.subscriberName
            phoneInput = subscriber.phoneNumber
            locationInput = subscriber.location
            statusInput = subscriber.status
            dobInput = subscriber.dateOfBirth
        }else{

            emailInput = "Enter Email"
            nameInput = "Enter Name"
            phoneInput = "Enter Phone"
            locationInput = "Enter Location"
            statusInput = "Enter Status"
            dobInput = ""

        }
    }

    fun validateFields():Boolean{
       return emailInput.isNotEmpty() && nameInput.isNotEmpty() &&
                phoneInput.isNotEmpty() && locationInput.isNotEmpty() &&
                statusInput.isNotEmpty() && dobInput.isNotEmpty()




    }

    private val _selectedUser:MutableStateFlow<Subscriber?> = MutableStateFlow(null)
    val selectedUser:StateFlow<Subscriber?> = _selectedUser

    fun getSelectSubscriber(id:Int){

            viewModelScope.launch {
                repository.getUser(id).collect{
                        user ->
                    _selectedUser.value = user
                }
            }



    }

    @Composable
    fun ShowSnackbar(
        snackbarHostState: SnackbarHostState,
        message: String,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(message, duration = duration)
        }
    }


}