package com.example.homework27.presentation.event

sealed class VehicleSearchPageEvents {
    data class LoadVehicles(val title:String): VehicleSearchPageEvents()
    data class QueryTextForSearch(val title:String): VehicleSearchPageEvents()
}