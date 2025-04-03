package com.example.dacs3.common.enum

sealed class LoadStatus(val description:String=""){
    class Init():LoadStatus()
    class Loading():LoadStatus()
    class Success(val success:String):LoadStatus()
    class Error(val error:String):LoadStatus(error)
}