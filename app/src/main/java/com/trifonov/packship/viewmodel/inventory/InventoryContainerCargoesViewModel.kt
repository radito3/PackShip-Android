package com.trifonov.packship.viewmodel.inventory

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.cargo.Cargo
import com.trifonov.packship.network.model.inventory.Inventory
import com.trifonov.packship.repository.InventoryRepository
import com.trifonov.packship.util.DispatcherProvider
import com.trifonov.packship.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class InventoryContainerCargoesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val inventoryRepository: InventoryRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(), LifecycleObserver {

    val inventoryId = savedStateHandle.getLiveData<String>("inventoryId")
    val containerId = savedStateHandle.getLiveData<String>("containerId")
    val cargoes = MutableLiveData<List<Cargo>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getCargoes() {

        val inventoryId = inventoryId.value
        val containerId = containerId.value

        if (inventoryId != null && containerId != null) {
            viewModelScope.launch(dispatcherProvider.ui) {
                when (val response = inventoryRepository.getInventoryContainerCargoes(inventoryId, containerId)) {
                    is PackShipResponse.Success -> {
                        Timber.e(response.data.toString())

                        cargoes.value = response.data
                    }
                    is PackShipResponse.Error -> {
                        Timber.d(response.message)
                        cargoes.value = listOf(Cargo("aaa", 200.0, 1000.0))
                    }
                }
            }
        }
    }
}