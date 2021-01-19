package com.trifonov.packship.network.endpoint

import com.trifonov.packship.network.model.shipment.Shipment
import retrofit2.http.GET

interface  ShipmentEndpoint {

    @GET("shipments")
    suspend fun getShipments() : List<Shipment>
}