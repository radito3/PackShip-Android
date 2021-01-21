package com.trifonov.packship.viewmodel.inventory

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.network.model.inventory.Inventory
import com.trifonov.packship.repository.InventoryRepository
import com.trifonov.packship.util.DispatcherProvider
import com.trifonov.packship.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class InventoryContainersViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val inventoryRepository: InventoryRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), LifecycleObserver {


    val containerDetails = SingleLiveEvent<String>()

    val id = savedStateHandle.getLiveData<String>("id")
    val containers = MutableLiveData<List<Container>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getContainers() {

        id.value?.let { inventoryId ->

            viewModelScope.launch(dispatcherProvider.ui) {

                when (val response = inventoryRepository.getInventoryContainers(inventoryId)) {

                    is PackShipResponse.Success -> {
                        Timber.d(response.data.toString())
                        containers.value = response.data
                    }

                    is PackShipResponse.Error -> {
                        Timber.e(response.message)
                    }
                }
            }
        }
    }

}