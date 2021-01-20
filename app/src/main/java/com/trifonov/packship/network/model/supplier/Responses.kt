package com.trifonov.packship.network.model.supplier

class Responses {
}

data class Supplier(
    val identity: String,
    val maximumWeight: Int,
    val loadedWeight: Int,
    val state: String,
    val loadedContainers: Int
)