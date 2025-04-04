package com.example.dacs3.repositories

import com.example.dacs3.model.CategoryResponse
import com.example.dacs3.model.LoginRequest
import com.example.dacs3.model.LoginRespon
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface Api {
    // api auth
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginRespon

    // api category
    @GET("category")
    suspend fun getCategory(@Header("Authorization") access_token: String): CategoryResponse
}