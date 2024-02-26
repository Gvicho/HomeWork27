package com.example.homework27.data.repository

import com.example.homework27.data.source.remote.common.HandleResponse
import com.example.homework27.data.source.remote.common.mapResultWrapper
import com.example.homework27.data.source.remote.mapper.toDomain
import com.example.homework27.data.source.remote.service.VehiclesApiService
import com.example.homework27.domain.model.Vehicle
import com.example.homework27.domain.repository.VehiclesRepository
import com.example.homework27.domain.result_wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow

class VehiclesRepositoryImpl(
    private val vehiclesApiService: VehiclesApiService,
    private val handleResponse: HandleResponse
) : VehiclesRepository {

    override fun getVehicles(title: String): Flow<ResultWrapper<List<Vehicle>>> {
        return handleResponse.safeApiCall {
            vehiclesApiService.searchByTitle(title)
        }.mapResultWrapper {list->
            list.map {
                it.toDomain()
            }
        }
    }

}
