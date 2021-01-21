package com.trifonov.packship.viewmodel.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.trifonov.packship.network.model.containers.Container
import com.trifonov.packship.util.SingleLiveEvent

class InventoryContainerItemViewModel(private val onContainerClicked: SingleLiveEvent<String>) : ViewModel() {

    private val container = MutableLiveData<Container>()

    val cargos = container.map { it.cargos.toString() }

    val maximumWeight = container.map { it.maximumWeight.toString() }

    val state = container.map { it.state }

    fun bind(item: Container) {
        container.value = item
    }
}