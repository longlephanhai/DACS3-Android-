package com.example.dacs3.repositories

import com.example.dacs3.model.LoginRequest
import com.example.dacs3.model.LoginRespon
import retrofit2.http.Body
import retrofit2.http.POST


interface Api {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginRespon
}