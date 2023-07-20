package com.example.mobilesub.ui.theme.view

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.PostModel
import com.example.mobilesub.data.models.RequestState
import com.example.mobilesub.data.models.Resource
import com.example.mobilesub.data.models.Subscriber
import com.example.mobilesub.data.repository.SubscriberRepository
import com.example.mobilesub.ui.theme.signin.login.GoogleSignState
import com.example.mobilesub.ui.theme.signin.login.SignInState
import com.example.mobilesub.ui.theme.signin.signup.SignUpState
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import java.util.UUID
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
//@ActivityRetainedScoped
class SharedViewModel @Inject constructor
    (private val repository: SubscriberRepository) : ViewModel() {


    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    private val _googleState = mutableStateOf(GoogleSignState())
    val googleState: State<GoogleSignState> = _googleState

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    var errorMessage by mutableStateOf("")

    val _myPost = MutableLiveData<List<PostModel>>()
    val myPost: LiveData<List<PostModel>> = _myPost

    val getData = mutableStateOf<List<PostModel>>(emptyList())


//     var posts by   mutableStateOf(emptyList<PostModel>())


    var emailInput by mutableStateOf("")
    var nameInput by mutableStateOf("")
    var phoneInput by mutableStateOf("")
    var locationInput by mutableStateOf("")
    var statusInput by mutableStateOf("")
    var dobInput by mutableStateOf("")
    var id by mutableStateOf("")

    var mExpended by mutableStateOf(value = false)
    var mTextFieldSize by mutableStateOf(Size.Zero)

    var isDatePickerVisible by mutableStateOf(false)

    private val _data = MutableStateFlow<RequestState<List<Subscriber>>>(RequestState.Idle)
    val data: StateFlow<RequestState<List<Subscriber>>> get() = _data

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    fun getPosts() {
        viewModelScope.launch {
            try {
                val posts = repository.getPost()
                _myPost.value = posts
            } catch (e: Exception) {
                errorMessage = e.message.toString()

            }
        }
    }


    fun getAllUserFlow() {
        _data.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllUsersFlow.collect {
                    _data.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _data.value = RequestState.Error(e)
        }

    }

    val allUsers: LiveData<List<Subscriber>> = repository.getAllUsers

    fun addUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = Subscriber(
                email = emailInput,
                phoneNumber = phoneInput,
                status = statusInput,
                location = locationInput,
                dateOfBirth = dobInput,
                subscriberName = nameInput,
                id = UUID.fromString(id)


            )


            repository.addUser(subscriber = user)
        }

    }

    fun addUserToWeb() {

        viewModelScope.launch(Dispatchers.IO) {

            val postUser = PostModel(
                email = emailInput,
                contact = phoneInput,
                status = statusInput,
                location = locationInput,
                doB = dobInput,
                name = nameInput,
                id = id

            )
            repository.addUserToWeb(subscriber = postUser)
        }

    }

    fun addUserFromWeb() {
        /*

        GET DATA FROM WEB AND SAVE TO DB
         */
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val subscribers = repository.fetchSubscribersFromWeb()
                subscribers.map { subscriber ->

                    val user = Subscriber(
                        email = subscriber.email,
                        phoneNumber = subscriber.contact,
                        status = subscriber.status,
                        location = subscriber.location,
                        dateOfBirth = subscriber.doB,
                        subscriberName = subscriber.name,
                        id = UUID.fromString(subscriber.id)

                    )
                    repository.addUser(subscriber = user)

                }

            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }

        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addUser()
            }

            Action.UPDATE -> {

            }

            else -> {}
        }
        Action.NO_ACTION


    }

    fun updateUserOnWeb() {

            viewModelScope.launch {
                val subscriber = PostModel(
                    email = emailInput,
                    contact = phoneInput,
                    status = statusInput,
                    location = locationInput,
                    doB = dobInput,
                    name = nameInput,
                    id = id

                )
                repository.updateUserOnWeb(subscriber)


        }
    }

    fun updateUserField(subscriber: Subscriber) {


        if (subscriber != null) {
            emailInput = subscriber.email
            nameInput = subscriber.subscriberName
            phoneInput = subscriber.phoneNumber.toString()
            locationInput = subscriber.location
            statusInput = subscriber.status
            dobInput = subscriber.dateOfBirth
        } else {

            emailInput = "Enter Email"
            nameInput = "Enter Name"
            phoneInput = "Enter Phone"
            locationInput = "Enter Location"
            statusInput = "Enter Status"
            dobInput = ""

        }

        if (subscriber.id == UUID.fromString("11a7a490-261a-11ee-be56-0242ac120002")) {

            emailInput = "Enter Email"
            nameInput = "Enter Name"
            phoneInput = "Enter Phone"
            locationInput = "Enter Location"
            statusInput = "Enter Status"
            dobInput = ""

        }
    }

    fun validateFields(): Boolean {
        return emailInput.isNotEmpty() && nameInput.isNotEmpty() &&
                phoneInput.isNotEmpty() && locationInput.isNotEmpty() &&
                statusInput.isNotEmpty() && dobInput.isNotEmpty()


    }

    private val _selectedUser: MutableStateFlow<Subscriber?> = MutableStateFlow(null)
    val selectedUser: StateFlow<Subscriber?> = _selectedUser

    fun getSelectSubscriber(id: UUID) {

        viewModelScope.launch {
            repository.getUser(id).collect { user ->
                _selectedUser.value = user
            }
        }


    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success"))

                }

                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))

                }

                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message.toString()))

                }
            }
        }
    }

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignState(isSuccess = result.data)
                }

                is Resource.Loading -> {
                    _googleState.value = GoogleSignState(isLoading = false)
                }

                is Resource.Error -> {
                    _googleState.value = GoogleSignState(isError = result.message!!)
                }
            }

        }
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))

                }

                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))

                }

                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))

                }
            }
        }
    }


}

