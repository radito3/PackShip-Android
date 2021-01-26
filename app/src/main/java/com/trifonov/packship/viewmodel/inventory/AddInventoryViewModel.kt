package com.trifonov.packship.viewmodel.inventory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trifonov.packship.network.PackShipResponse
import com.trifonov.packship.network.model.inventory.InventoryBody
import com.trifonov.packship.repository.InventoryRepository
import com.trifonov.packship.util.DispatcherProvider
import com.trifonov.packship.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class AddInventoryViewModel @ViewModelInject constructor(
    private val inventoryRepository: InventoryRepository,
    private val dispatcherProvider: DispatcherProvider

) : ViewModel() {

    val back = SingleLiveEvent<Unit>()

    val width = MutableLiveData<String>()
    val height = MutableLiveData<String>()

    fun onSaveButtonClicked() {
        val width = width.value
        val height = height.value

        if(width != null && height !=null) {
            val inventory = InventoryBody(width.toDouble(), height.toDouble())

            viewModelScope.launch(dispatcherProvider.ui) {
                when (val response = inventoryRepository.addInventory(inventory)) {
                    is PackShipResponse.Success -> {
                       back.call()
                    }
                    is PackShipResponse.Error -> {
                        Timber.i(response.message)
                    }
                }
            }
        }
    }
}