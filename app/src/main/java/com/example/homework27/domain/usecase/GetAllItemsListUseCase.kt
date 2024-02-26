package com.example.homework27.domain.usecase

import com.example.homework27.domain.model.Vehicle
import javax.inject.Inject

class GetAllItemsListUseCase @Inject constructor() {

    suspend operator fun invoke(vehicles:List<Vehicle>):List<Vehicle>{
        return getChildren(vehicles)
    }

    private suspend fun getChildren(vehicles:List<Vehicle>):List<Vehicle>{
        val list = mutableListOf<Vehicle>()

        for (element in vehicles) {

            val children = getChildren(element.children)

            list += children
            list += element
        }
        return list
    }
}