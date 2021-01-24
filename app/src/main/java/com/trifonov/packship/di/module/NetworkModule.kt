package com.trifonov.packship.di.module

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.moczul.ok2curl.CurlInterceptor
import com.trifonov.packship.network.endpoint.ContainerEndpoint
import com.trifonov.packship.network.endpoint.InventoryEndpoint
import com.trifonov.packship.network.endpoint.ShipmentEndpoint
import com.trifonov.packship.network.endpoint.SupplierEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesObjectMapper(): ObjectMapper {
        val objectMapper = jacksonObjectMapper()

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)

        return objectMapper
    }

    @Provides
    @Singleton
    fun providesJacksonConverterFactory(objectMapper: ObjectMapper): JacksonConverterFactory {
        return JacksonConverterFactory.create(objectMapper)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(CurlInterceptor { message -> Timber.d(message) })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(converterFactory: JacksonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideInventoryEndpoint(retrofit: Retrofit): InventoryEndpoint {
        return retrofit.create(InventoryEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideShipmentEndpoint(retrofit: Retrofit): ShipmentEndpoint {
        return retrofit.create(ShipmentEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideSupplierEndpoint(retrofit: Retrofit): SupplierEndpoint {
        return retrofit.create(SupplierEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideContainerEndpoint(retrofit: Retrofit): ContainerEndpoint {
        return retrofit.create(ContainerEndpoint::class.java)
    }
}