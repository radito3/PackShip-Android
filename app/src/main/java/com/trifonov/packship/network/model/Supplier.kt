package com.trifonov.packship.network.model

data class Supplier(
    val identity: String,
    val maximumWeight: Int,
    val loadedWeight: Int,
    val state: String,
    val loadedContainers: Int
)

