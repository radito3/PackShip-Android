package com.trifonov.packship.viewmodel.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.trifonov.packship.network.model.cargo.Cargo

class InventoryContainerCargoItemViewModel() : ViewModel() {

    private val cargo = MutableLiveData<Cargo>()

    val weight = cargo.map { it.weight.toString() }

    val price = cargo.map { it.price.toString() }

    fun bind(item : Cargo) {
        cargo.value = item
    }
}