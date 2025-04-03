package com.example.dacs3.model

import com.example.dacs3.common.enum.LoadStatus

data class LoginRespon(
    val statusCode: Int,
    val message: String,
    val data: Data,
)

data class LoginRequest(
    val email: String="",
    val password: String="",
    val status: LoadStatus = LoadStatus.Init()
)

data class Data(
    val access_token: String,
    val user: User
)

data class User(
    val name: String,
    val email: String,
    val _id: String,
    val role: String
)