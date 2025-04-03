package com.example.dacs3.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.dacs3.repositories.Api
import com.example.dacs3.repositories.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainLog: MainLog,
    private val api: Api
): ViewModel(){
}