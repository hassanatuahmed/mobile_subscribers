package com.example.mobilesub.data.service

import com.example.mobilesub.data.models.PostModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPost(): List<PostModel>
}