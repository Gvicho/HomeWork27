package com.example.homework27.domain.usecase

import com.example.homework27.domain.model.Vehicle
import javax.inject.Inject

class GetDepthOfChildUseCase @Inject constructor() {

    operator fun invoke(list:List<Vehicle>){
        giveAllVehicleDepth(0,list)
    }

    private fun giveAllVehicleDepth(depth:Int, list:List<Vehicle>){
        for (element in list) {
            element.parentsNum=depth
            giveAllVehicleDepth(depth+1,element.children)
        }
    }

}