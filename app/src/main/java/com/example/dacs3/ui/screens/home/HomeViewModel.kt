package com.example.dacs3.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dacs3.common.enum.LoadStatus
import com.example.dacs3.model.DataCategory
import com.example.dacs3.repositories.Api
import com.example.dacs3.repositories.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainLog: MainLog,
    private val api: Api,

) : ViewModel() {
    val _uiState = MutableStateFlow<List<DataCategory>>(emptyList())
    val uiState = _uiState.asStateFlow()
    fun getCategories(accessToken: String) {
        viewModelScope.launch {
            try {
                val response = requireNotNull(api).getCategory("Bearer $accessToken")
                if (response.statusCode == 200) {
                    _uiState.value = response.data
                    _uiState.value = _uiState.value.map {
                        it.copy(status = LoadStatus.Success(response.message))
                    }
                    Log.d("Response category Success", "Success: $response")
                } else {
                    _uiState.value = _uiState.value.map {
                        it.copy(status = LoadStatus.Error(response.message))
                    }
                    Log.d("Response category Error", "Error: $response")
                }
            } catch (ex: Exception) {
                _uiState.value =
                    _uiState.value.map { it.copy(status = LoadStatus.Error(ex.message.toString())) }
                Log.e("Response Error", "Error: $ex")
            }
        }
    }
}