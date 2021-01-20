package com.trifonov.packship.repository

import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.endpoint.ContainerEndpoint
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.network.safeApiCall
import javax.inject.Inject

class ContainerRepository @Inject constructor(private val api: ContainerEndpoint) {

    suspend fun getContainers(): PackShipResponse<List<Container>> {
        return safeApiCall { api.getContainers() }
    }
}