package com.trifonov.packship.network.model.containers

import com.trifonov.packship.network.enums.container.ContainerState

data class Container(
    val identity: String,
    val maximumWeight: Int,
    val state: ContainerState,
    val cargos: Int
)
