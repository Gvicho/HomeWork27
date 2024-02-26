package com.example.homework27.data.source.remote.model

import com.squareup.moshi.Json

data class VehicleDto(
    val id: String,
    val name: String,
    @Json(name = "name_de")
    val nameDe: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "bgl_number")
    val bglNumber: String?,
    @Json(name = "bgl_variant")
    val bglVariant: String?,
    @Json(name = "order_id")
    val orderId: Int?,
    val main: String?,
    val children: List<VehicleDto>
) {
}