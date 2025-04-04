package com.example.dacs3.repositories

import com.example.dacs3.model.CategoryResponse
import com.example.dacs3.model.LoginRequest
import com.example.dacs3.model.LoginRespon
import javax.inject.Inject

class ApiImpl @Inject constructor(
    private val apiClient: ApiClient
) : Api {
    override suspend fun login(loginRequest: LoginRequest): LoginRespon {
        return apiClient.api.login(loginRequest)
    }

    override suspend fun getCategory(accessToken: String): CategoryResponse {
        return apiClient.api.getCategory(accessToken)
    }
}