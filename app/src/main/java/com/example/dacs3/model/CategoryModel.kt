package com.example.dacs3.model

import com.example.dacs3.common.enum.LoadStatus

data class CategoryResponse(
    val statusCode: Int,
    val message: String,
    val data: List<DataCategory>
)

data class DataCategory(
    val _id: String,
    val title: String,
    val description: String,
    val image: String,
    val slug: String,
    val status: LoadStatus = LoadStatus.Init(),
    val isActive: Boolean,
    val createdBy: CreatedBy,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)

data class CreatedBy(
    val _id: String,
    val email: String
)

