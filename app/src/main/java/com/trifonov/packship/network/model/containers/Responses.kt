package com.trifonov.packship.network.model.containers

data class Container(
    val identity: String,
    val maximumWeight: Int,
    val state: String,
    val cargos: Int
)
