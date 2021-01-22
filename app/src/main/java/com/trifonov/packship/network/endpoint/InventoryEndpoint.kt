package com.trifonov.packship.network.endpoint

import com.trifonov.packship.network.model.cargo.Cargo
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.network.model.inventory.Inventory
import com.trifonov.packship.network.model.inventory.InventoryBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InventoryEndpoint {

    @GET("inventories")
    suspend fun getInventories(): List<Inventory>

    @GET("inventories/{inventoryId}")
    suspend fun getInventory(@Path("inventoryId") id: String): Inventory

    @POST("inventories")
    suspend fun addInventory(@Body inventory: InventoryBody)

    @GET("inventories/{inventoryId}/containers")
    suspend fun getInventoryContainers(@Path("inventoryId") id: String): List<Container>

    @GET("(inventories/{inventoryId}/containers/{containerId}/cargos")
    suspend fun getInventoryContainerCargoes(
        @Path("inventoryId") inventoryId: String,
        @Path("containerId") containerId: String
    ) : List<Cargo>
}

