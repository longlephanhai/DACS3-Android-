package com.example.dacs3.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dacs3.common.enum.LoadStatus
import com.example.dacs3.model.LoginRequest
import com.example.dacs3.repositories.Api
import com.example.dacs3.repositories.DataStoreManager
import com.example.dacs3.repositories.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: Api,
    private val mainLog: MainLog,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val _uiState = MutableStateFlow(LoginRequest())
    val uiState = _uiState.asStateFlow()
    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun reset() {
        _uiState.value = _uiState.value.copy(status = LoadStatus.Init())
    }

    // Hàm lưu access_token sau khi đăng nhập thành công
    fun saveToken(token: String) {
        viewModelScope.launch {
            dataStoreManager.saveAccessToken(token)
        }
    }

    // Hàm lấy access_token
    suspend fun getToken(): String? {
        return dataStoreManager.getAccessToken().first()
    }

    // Hàm đăng xuất (xoá token)
    fun logout() {
        viewModelScope.launch {
            dataStoreManager.clearAccessToken()
        }
    }

    fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(status = LoadStatus.Loading())
            try {
                val loginRequest =
                    LoginRequest(email = _uiState.value.email, password = _uiState.value.password)
                val response = requireNotNull(api).login(loginRequest)
                if (response.statusCode == 201) {
                    saveToken(response.data.access_token)
                    _uiState.value =
                        _uiState.value.copy(status = LoadStatus.Success(response.message))
                } else {
                    _uiState.value =
                        _uiState.value.copy(status = LoadStatus.Error(response.message))
                }
            } catch (ex: Exception) {
                _uiState.value =_uiState.value.copy(status = LoadStatus.Error(ex.message.toString()))
                Log.e("Response Error", "Error: $ex")
            }
        }
    }
}