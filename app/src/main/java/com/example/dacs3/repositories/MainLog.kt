package com.example.dacs3.repositories

interface MainLog {
    fun i(tag: String, msg: String)
    fun d(tag: String, msg: String)
    fun e(tag: String, msg: String)
}