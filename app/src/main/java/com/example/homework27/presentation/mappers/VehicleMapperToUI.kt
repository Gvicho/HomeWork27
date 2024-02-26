package com.example.homework27.presentation.mappers

import com.example.homework27.domain.model.Vehicle
import com.example.homework27.presentation.model.VehicleUI

fun Vehicle.toUI(): VehicleUI {
    return VehicleUI(
        id = id,
        name = name,
        parentsNum = parentsNum
    )
}