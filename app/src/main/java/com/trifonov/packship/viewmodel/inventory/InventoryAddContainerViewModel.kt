package com.trifonov.packship.viewmodel.inventory

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.containers.ContainerBody
import com.trifonov.packship.repository.InventoryRepository
import com.trifonov.packship.util.DispatcherProvider
import com.trifonov.packship.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class InventoryAddContainerViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val inventoryRepository: InventoryRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    val back = SingleLiveEvent<Unit>()
    val id = savedStateHandle.getLiveData<String>("id")
    val width = MutableLiveData<String>()
    val height = MutableLiveData<String>()
    val maxWeight = MutableLiveData<String>()

    fun onSaveButtonClicked() {
        val width = width.value
        val height = height.value
        val maxWeight = maxWeight.value

        if (width == null || height == null || maxWeight == null) {
            warnMissingValues(width, height, maxWeight)
            return
        }

        val container = ContainerBody(width.toDouble(), height.toDouble(), maxWeight.toDouble())

        id.value?.let { inventoryId -> callApi(inventoryId, container) }
    }

    private fun warnMissingValues(width: String?, height: String?, maxWeight: String?) {
        //TODO display popup with a warning about missing value
    }

    private fun callApi(inventoryId: String, container: ContainerBody) {
        Timber.d("creating container for inventory: $inventoryId")
        viewModelScope.launch(dispatcherProvider.ui) {
            when (val response = inventoryRepository.addInventoryContainer(inventoryId, container)) {
                is PackShipResponse.Success -> back.call()
                is PackShipResponse.Error -> Timber.i(response.message)
            }
        }
    }
}