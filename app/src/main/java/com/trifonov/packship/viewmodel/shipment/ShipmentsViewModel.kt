package com.trifonov.packship.viewmodel.shipment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.shipment.Shipment
import com.trifonov.packship.repository.ShipmentRepository
import com.trifonov.packship.util.DispatcherProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class ShipmentsViewModel @ViewModelInject constructor(
    private val shipmentRepository: ShipmentRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), LifecycleObserver {

    val shipments = MutableLiveData<List<Shipment>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getInventories() {
        viewModelScope.launch(dispatcherProvider.ui) {
            when (val response = shipmentRepository.getShipments()) {
                is PackShipResponse.Success -> {
                    Timber.e(response.data.toString())
                    shipments.value = response.data
                }
                is PackShipResponse.Error -> {
                    Timber.d(response.message)
                }
            }
        }
    }
}