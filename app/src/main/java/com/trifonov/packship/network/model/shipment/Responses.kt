package com.trifonov.packship.network.model.shipment

data class Shipment(
    val identity: String,
    val revenue: Double,
    val transportationExpenses: Double,
    val status: String,
    val packedContainers: Int,
    val assignments: Int
)

//[Shipment{Jump to definition
//    identity	string
//            revenue	number
//            transportationExpenses	number
//            status	string
//            packedContainers	number
//            assignments	number
//
//}]