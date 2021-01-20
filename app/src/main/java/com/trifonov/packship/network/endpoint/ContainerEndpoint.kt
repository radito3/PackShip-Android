package com.trifonov.packship.network.endpoint

import com.trifonov.packship.network.model.containers.Container
import retrofit2.http.GET

interface ContainerEndpoint {

    @GET("containers")
    suspend fun getContainers() : List<Container>
}