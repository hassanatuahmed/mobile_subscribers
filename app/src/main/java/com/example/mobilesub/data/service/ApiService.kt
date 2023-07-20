package com.example.mobilesub.data.service

import com.example.mobilesub.data.models.PostModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/api/mobile/subscribers")
    suspend fun getPost(): List<PostModel>

    @POST("/api/mobile/subscribers")
    suspend fun addSubscriber(@Body subscriber: PostModel):Call<Void>

    @PUT("/api/mobile/subscribers/{id}")

    suspend fun updateUser(@Path("id") id: String, @Body subscriber: PostModel):Call<Void>
}