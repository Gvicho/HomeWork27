package com.example.homework27.domain.repository

import com.example.homework27.domain.model.Vehicle
import com.example.homework27.domain.result_wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface VehiclesRepository {
    fun getVehicles(title: String): Flow<ResultWrapper<List<Vehicle>>>
}