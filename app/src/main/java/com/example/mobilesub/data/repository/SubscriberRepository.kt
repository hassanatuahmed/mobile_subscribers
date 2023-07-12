package com.example.mobilesub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilesub.data.SubscriberDao
import com.example.mobilesub.data.models.Resource
import com.example.mobilesub.data.models.Subscriber
import com.example.mobilesub.data.service.ApiService
import com.google.android.gms.common.api.Response
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject



@ViewModelScoped
class SubscriberRepository @Inject constructor(
    private val subscriberDao: SubscriberDao,
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService
) {
    val getAllUsers: LiveData<List<Subscriber>> = subscriberDao.getAllUser()

    val getAllUsersFlow: Flow<List<Subscriber>> = subscriberDao.getAllUserFlow()



    fun getUser(id:Int) : Flow<Subscriber> {
        return subscriberDao.getSubscriber(id);
    }

    suspend fun addUser(subscriber: Subscriber){
        subscriberDao.insertSubscriber(subscriber)
    }


    fun loginUser(email:String,password:String): Flow<Resource<AuthResult>>{
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
    fun registerUser(email: String,password: String):Flow<Resource<AuthResult>>{
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>{
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }




}