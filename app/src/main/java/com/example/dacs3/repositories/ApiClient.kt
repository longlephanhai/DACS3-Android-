package com.example.dacs3.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.getValue

class ApiClient @Inject constructor() {
    private val BASE_URL = "http://10.0.2.2:8080/api/v1/"

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}
