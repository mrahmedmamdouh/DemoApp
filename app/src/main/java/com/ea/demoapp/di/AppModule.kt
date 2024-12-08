package com.ea.demoapp.di

import android.content.Context
import com.ea.demoapp.di.DatabaseModule.provideDatabase
import com.ea.demoapp.di.DatabaseModule.provideMedicineDao
import com.ea.demoapp.di.NetworkModule.provideMedicineApi
import com.ea.demoapp.di.NetworkModule.provideRetrofit
import com.ea.demoapp.data.datasource.LocalDataSource
import com.ea.demoapp.data.datasource.RemoteDataSource
import com.ea.demoapp.data.repo.RepositoryImpl
import com.ea.demoapp.domain.repo.MedicineRepository
import com.ea.demoapp.domain.usecase.GetMedicinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetMedicinesUseCase(
        repository: MedicineRepository
    ): GetMedicinesUseCase {
        return GetMedicinesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource(provideMedicineApi(provideRetrofit()))
    }
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataSource(provideMedicineDao(provideDatabase(context)))
    }
    @Provides
    @Singleton
    fun provideMedicineRepository(@ApplicationContext context: Context): MedicineRepository {
        return RepositoryImpl(provideRemoteDataSource(), provideLocalDataSource(context))
    }


}
