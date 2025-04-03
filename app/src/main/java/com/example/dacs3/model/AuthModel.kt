package com.example.dacs3.model

data class LoginRespon(
    val statusCode: Int = -1,
    val message: String = "",
    val data: Data = Data("", User("", "", "", "")),
)

data class LoginRequest(
    val email: String,
    val password: String
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