package com.trifonov.packship.network.model.inventory

data class Inventory(
    val identity: String,
    val width: Double,
    val height: Double,
    val acceptedContainers: Int
)
