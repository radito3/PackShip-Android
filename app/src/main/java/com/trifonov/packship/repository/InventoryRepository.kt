package com.trifonov.packship.repository

import com.trifonov.packship.network.endpoint.InventoryEndpoint
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.cargo.Cargo
import com.trifonov.packship.network.model.cargo.CargoBody
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.network.model.inventory.Inventory
import com.trifonov.packship.network.model.inventory.InventoryBody
import com.trifonov.packship.network.safeApiCall
import javax.inject.Inject

class InventoryRepository @Inject constructor(private val api: InventoryEndpoint) {

    suspend fun getInventories(): PackShipResponse<List<Inventory>> {
        return safeApiCall { api.getInventories() }
    }

    suspend fun getInventory(id: String): PackShipResponse<Inventory> {
        return safeApiCall { api.getInventory(id) }
    }

    suspend fun addInventory(inventory: InventoryBody): PackShipResponse<Unit> {
        return safeApiCall { api.addInventory(inventory) }
    }

    suspend fun getInventoryContainers(id: String): PackShipResponse<List<Container>> {
        return safeApiCall { api.getInventoryContainers(id) }
    }

    suspend fun getInventoryContainerCargoes(inventoryId : String, containerId : String) : PackShipResponse<List<Cargo>> {
        return safeApiCall { api.getInventoryContainerCargoes(inventoryId, containerId) }
    }

    suspend fun addInventoryContainerCargo(inventoryId: String, containerId: String, cargoBody: CargoBody) : PackShipResponse<Unit> {
        return safeApiCall { api.addInventoryContainerCargo(inventoryId, containerId, cargoBody) }
    }
}