package com.example.homework27.presentation.state

import com.example.homework27.presentation.model.VehicleUI

data class VehiclesSearchPageState(
    val isLoading:Boolean = false,
    val vehiclesList:List<VehicleUI>? = null
)