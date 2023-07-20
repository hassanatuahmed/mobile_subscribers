package com.example.mobilesub.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mobilesub.data.SubscriberDao
import com.example.mobilesub.data.models.PostModel
import com.example.mobilesub.data.models.Resource
import com.example.mobilesub.data.models.Subscriber
import com.example.mobilesub.data.service.ApiService
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID
import kotlin.concurrent.thread


@ViewModelScoped
class SubscriberRepository @Inject constructor(
    private val subscriberDao: SubscriberDao,
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService
) {
    val getAllUsers: LiveData<List<Subscriber>> = subscriberDao.getAllUser()

    val getAllUsersFlow: Flow<List<Subscriber>> = subscriberDao.getAllUserFlow()


    suspend fun getPost() : List<PostModel>{
        return apiService.getPost()
    }

    suspend fun fetchSubscribersFromWeb(): List<PostModel> {
        return apiService.getPost()
    }

    suspend fun updateUserOnWeb(subscriber: PostModel){
        try {
            apiService.updateUser(subscriber.id,subscriber).enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    TODO("Not yet implemented")

                    if(response.isSuccessful){

                    }else{
                        println("update failed")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("Not yet implemented")
                    println("update failed")
                    Log.d("Updating User","Error adding subscriber ${t.printStackTrace()}")

                }

            })

        }catch (e:Exception){
            Log.d("response", "debug $e")

        }
    }

    suspend fun addUserToWeb(subscriber: PostModel){

        try{

            apiService.addSubscriber(subscriber).enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                isLoading.value = false
                    response.body()?.let{
                        Log.d("Response", "${response.body()}")

//                    response.value = "Subscriber added successfully"
                        Log.d("Adding User","Subscriber added successfully")

                    }
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("Not yet implemented")
//                response.value = "Error adding subscriber"
                    Log.d("Adding User","Error adding subscriber ${t.printStackTrace()}")


                }

            })

        }catch (e:Exception){
            Log.d("response", "debug $e")
        }

    }



    fun getUser(id:UUID) : Flow<Subscriber> {
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