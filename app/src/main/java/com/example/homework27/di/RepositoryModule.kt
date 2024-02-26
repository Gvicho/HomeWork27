package com.example.homework27.di

import com.example.homework27.data.repository.VehiclesRepositoryImpl
import com.example.homework27.data.source.remote.common.HandleResponse
import com.example.homework27.data.source.remote.service.VehiclesApiService
import com.example.homework27.domain.repository.VehiclesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideVehiclesRepository(
        vehiclesApiService: VehiclesApiService,
        handleResponse: HandleResponse
    ): VehiclesRepository {
        return VehiclesRepositoryImpl(
            vehiclesApiService = vehiclesApiService,
            handleResponse = handleResponse
        )
    }
}