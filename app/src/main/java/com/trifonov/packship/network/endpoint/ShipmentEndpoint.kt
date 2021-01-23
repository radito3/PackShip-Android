package com.trifonov.packship.network.endpoint

import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.network.model.shipment.Shipment
import retrofit2.http.GET
import retrofit2.http.Path

interface  ShipmentEndpoint {

    @GET("shipments")
    suspend fun getShipments() : List<Shipment>

    @GET("shipments/{shipmentId}/containers")
    suspend fun getShipmentContainers(@Path ("shipmentId") id : String) : List<Container>
}