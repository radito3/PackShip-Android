package com.trifonov.packship.viewmodel.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.trifonov.packship.network.model.inventory.Inventory
import com.trifonov.packship.util.SingleLiveEvent

class InventoryItemViewModel(private val onInventoryClicked: SingleLiveEvent<String>) :
    ViewModel() {

    private val inventory = MutableLiveData<Inventory>()

    val width = inventory.map { it.width.toString() }

    val height = inventory.map { it.width.toString() }

    val acceptedContainers = inventory.map { it.acceptedContainers.toString() }


    fun bind(item: Inventory) {
        inventory.value = item
    }

    fun onItemClicked() {
        inventory.value?.let {
            onInventoryClicked.value = it.identity
        }
    }
}