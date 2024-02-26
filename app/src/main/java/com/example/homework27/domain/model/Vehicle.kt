package com.example.homework27.domain.model

class Vehicle(
    val id: String,
    val name: String,
    val nameDe: String,
    val createdAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: String?,
    val children: List<Vehicle>,
    var parentsNum:Int = 0
) {
}