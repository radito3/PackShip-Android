package com.trifonov.packship.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class PackShipResponse<out R> {
    data class Success<out T>(val data: T) : PackShipResponse<T>()
    data class Error(val exception: Exception, val message: String? = null) :
        PackShipResponse<Nothing>()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): PackShipResponse<T> = withContext(Dispatchers.IO) {
    try {
        PackShipResponse.Success(apiCall.invoke())
    } catch (exception: Exception) {
        PackShipResponse.Error(exception, exception.message)
    }
}