package com.example.dacs3.di

import com.example.dacs3.repositories.Api
import com.example.dacs3.repositories.ApiImpl
import com.example.dacs3.repositories.MainLog
import com.example.dacs3.repositories.MainLogImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindMainLog(
        log: MainLogImpl
    ): MainLog

    @Binds
    @Singleton
    abstract fun bindApi(
        api: ApiImpl
    ): Api
}