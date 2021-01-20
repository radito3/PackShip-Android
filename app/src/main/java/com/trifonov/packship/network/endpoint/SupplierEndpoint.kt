package com.trifonov.packship.network.endpoint

import com.trifonov.packship.network.model.supplier.Supplier
import retrofit2.http.GET

interface SupplierEndpoint {
    @GET("suppliers")
    suspend fun getSuppliers() : List<Supplier>
}