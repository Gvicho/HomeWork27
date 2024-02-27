package com.example.homework27.domain.usecase

import com.example.homework27.domain.model.Vehicle
import com.example.homework27.domain.repository.VehiclesRepository
import com.example.homework27.domain.result_wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVehiclesListUseCase @Inject constructor(
    private val vehiclesRepository:VehiclesRepository,
    private val getDepthOfChildUseCase: GetDepthOfChildUseCase,
    private val getAllItemsListUseCase: GetAllItemsListUseCase
) {

    operator fun invoke(title: String): Flow<ResultWrapper<List<Vehicle>>> {
        return vehiclesRepository.getVehicles(title).map { result ->
            if (result is ResultWrapper.Success) {
                val vehiclesList = result.data!!


                var newList = getAllItemsListUseCase(vehiclesList)

                newList = newList.filter {
                    it.name.contains(title)
                }

                getDepthOfChildUseCase(vehiclesList)

                ResultWrapper.Success(newList)
            } else {
                result
            }
        }
    }

}