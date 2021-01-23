package com.trifonov.packship.viewmodel.shipment

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.repository.ShipmentRepository
import com.trifonov.packship.util.DispatcherProvider
import com.trifonov.packship.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class ShipmentContainersViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val shipmentRepository: ShipmentRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), LifecycleObserver {

    val onContainerClicked = SingleLiveEvent<String>()

    val id = savedStateHandle.getLiveData<String>("id")

    val containers = MutableLiveData<List<Container>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getShipments() {

        id.value?.let {

            viewModelScope.launch(dispatcherProvider.ui) {
                when (val response = shipmentRepository.getShipmentContainers(it)) {
                    is PackShipResponse.Success -> {
                        containers.value = response.data
                        Timber.d(response.data.toString())
                    }
                    is PackShipResponse.Error -> {
                        Timber.e(response.message)
                    }
                }
            }
        }
    }
}