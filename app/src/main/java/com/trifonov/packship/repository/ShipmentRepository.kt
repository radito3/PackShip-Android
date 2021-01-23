package com.trifonov.packship.repository

import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.endpoint.ShipmentEndpoint
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.network.model.shipment.Shipment
import com.trifonov.packship.network.safeApiCall
import javax.inject.Inject

class ShipmentRepository @Inject constructor(private val api: ShipmentEndpoint) {

    suspend fun getShipments(): PackShipResponse<List<Shipment>> {
        return safeApiCall { api.getShipments() }
    }

    suspend fun getShipmentContainers(id: String) : PackShipResponse<List<Container>> {
        return safeApiCall { api.getShipmentContainers(id) }
    }
}