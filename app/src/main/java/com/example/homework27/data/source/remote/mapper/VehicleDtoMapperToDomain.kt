package com.example.homework27.data.source.remote.mapper

import com.example.homework27.data.source.remote.model.VehicleDto
import com.example.homework27.domain.model.Vehicle

fun VehicleDto.toDomain(): Vehicle {
    return Vehicle(
        id = id,
        name = name,
        nameDe = nameDe,
        createdAt = createdAt,
        bglNumber = bglNumber,
        bglVariant = bglVariant,
        orderId = orderId,
        main = main,
        children = children.map {
            it.toDomain()
        }
    )
}